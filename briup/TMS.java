    package com.imooc;
	import java.util.Scanner;
	public class TMS {
		private teacher[] teas;	
		private int index;		
		public TMS(){
			this.teas = new teacher[3];
			this.index = 0;
		}
		public void save(teacher tea){
			if(index >= teas.length){
				teacher[] demo = new teacher[teas.length+3];
				System.arraycopy(teas,0,demo,0,index);
				teas = demo;
			}
			teas[index++] = tea;
		}
		public teacher[]  findAll(){
			teacher[] demo = new teacher[index];
			System.arraycopy(teas,0,demo,0,index);
			return demo;
		}
		public teacher findById(long id){
			int num = findIndexById(id);
			return num==-1?null:teas[num];
		}
		public int findIndexById(long id){
			int num = -1;
			for(int i=0;i<index;i++){
				if(teas[i].getId() == id){
					num = i;
					break;
				}
			}
			return num;
		}
		public void deleteById(long id){
			int num = findIndexById(id);
			for(int i=num;i<index-1;i++){
				teas[i] = teas[i+1];
			}
			teas[--index] = null;
		}
		public void update(teacher newtea){
		    for(int i=0;i<index;i++){ 
			if(teas[i].getId()==newtea.getId())
			{
				teas[i].setAge(newtea.getAge());
				teas[i].setName(newtea.getName());
			}
				}
		}
		public void menu(){
			System.out.println("***********ѧ����Ϣ����ϵͳ***********");
			System.out.println("*1. ��ѯ���н�ʦ��Ϣ");
			System.out.println("*2. ¼���ʦ��Ϣ");
			System.out.println("*3. ɾ����ʦ��Ϣ");
			System.out.println("*4. �鿴��ʦ��Ϣ");
			System.out.println("*5. ���Ľ�ʦ��Ϣ");
			System.out.println("*help. ����");
			System.out.println("*exit. �˳�");
			System.out.println("**************************************");
		}
		public static void main(String[] args){
			TMS sms = new TMS();	
			Scanner sc = new Scanner(System.in);
			sms.menu();
			while(true){
				System.out.print("�����빦�ܱ�ţ�");
				String option = sc.nextLine();
				switch(option){
					case "1":{
						System.out.println("���������н�ʦ����Ϣ��");
						teacher [] arr = sms.findAll();
						for(teacher tea : arr){
							System.out.println(tea);
						}
						System.out.println("�ܼ� "+sms.index+"��");
						break;
					}
					case "2":{
						while(true){
							System.out.println("�������ʦ��id#name#age���������롾break���������˵�");
							String teaStr = sc.nextLine();
							if(teaStr.equals("break")){
								break;
							}
							String[] teaArr = teaStr.split("#");
							long id = Long.parseLong(teaArr[0]);
							String name = teaArr[1];
							int age = Integer.parseInt(teaArr[2]);
							teacher tea = new teacher(id,name,age);
							sms.save(tea);
							System.out.println("¼��ɹ���");
						}
						break;
					}
					case "3":{
						while(true){
							System.out.println("������Ҫɾ����ʦ�ġ�id���������롾break��������Ŀ¼");
							String idStr = sc.nextLine();
							if(idStr.equals("break")){
								break;
							}
							long id = Long.parseLong(idStr);
							teacher tea = sms.findById(id);
							if(tea == null){
								System.out.println("�ý�ʦ�����ڣ�");
								continue;
							}
							sms.deleteById(id);
							System.out.println("ɾ���ɹ���");
						}

						break;
					}
					case "4":{
						while(true){
							System.out.println("������Ҫ���ҽ�ʦ�ġ�id���������롾break��������Ŀ¼");
							String idStr = sc.nextLine();
							if(idStr.equals("break")){
								break;
							}
							long id = Long.parseLong(idStr);
							teacher tea= sms.findById(id);
							if(tea == null){
								System.out.println("�ý�ʦ�����ڣ�");
								continue;
							}
							System.out.println(tea);
						}
						break;
					}
					case "5":{
						while(true){
							System.out.println("������Ҫ���ĵĽ�ʦ�ġ�id���������롾break��������Ŀ¼");
							String idStr = sc.nextLine();
							if(idStr.equals("break")){
								break;
							}
							long id = Long.parseLong(idStr);
							teacher tea= sms.findById(id);
							if(tea == null){
								System.out.println("�ý�ʦ�����ڣ�");
								continue;
							}
							System.out.println("Ҫ�޸ĵĽ�ʦ��"+tea);
							 System.out.println("�������ʦ������#��������break");
							 Scanner in=new Scanner(System.in);
							 String a=in.nextLine();
							 if(a.equals("break")){
									break;
								}
							 String [] b=a.split("#");
							 String name=b[1];
							 int  age=Integer.parseInt(b[0]); 
							 teacher newtea=new teacher(id,name,age);
							 sms.update(newtea);
						}
						break;
					}
					case "exit":{
						System.out.println("bye bye!��ӭ�ٴ�ʹ�ã�");
						System.exit(0);
					}
					case "help":{
						sms.menu();
						break;
					}
					default:
						System.out.println("��������");
				}
			}
		}
	}

