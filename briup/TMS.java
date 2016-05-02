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
			System.out.println("***********学生信息管理系统***********");
			System.out.println("*1. 查询所有教师信息");
			System.out.println("*2. 录入教师信息");
			System.out.println("*3. 删除教师信息");
			System.out.println("*4. 查看教师信息");
			System.out.println("*5. 更改教师信息");
			System.out.println("*help. 帮助");
			System.out.println("*exit. 退出");
			System.out.println("**************************************");
		}
		public static void main(String[] args){
			TMS sms = new TMS();	
			Scanner sc = new Scanner(System.in);
			sms.menu();
			while(true){
				System.out.print("请输入功能编号：");
				String option = sc.nextLine();
				switch(option){
					case "1":{
						System.out.println("以下是所有教师的信息：");
						teacher [] arr = sms.findAll();
						for(teacher tea : arr){
							System.out.println(tea);
						}
						System.out.println("总计 "+sms.index+"人");
						break;
					}
					case "2":{
						while(true){
							System.out.println("请输入教师【id#name#age】或者输入【break】返回主菜单");
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
							System.out.println("录入成功！");
						}
						break;
					}
					case "3":{
						while(true){
							System.out.println("请输入要删除教师的【id】或者输入【break】返回主目录");
							String idStr = sc.nextLine();
							if(idStr.equals("break")){
								break;
							}
							long id = Long.parseLong(idStr);
							teacher tea = sms.findById(id);
							if(tea == null){
								System.out.println("该教师不存在！");
								continue;
							}
							sms.deleteById(id);
							System.out.println("删除成功！");
						}

						break;
					}
					case "4":{
						while(true){
							System.out.println("请输入要查找教师的【id】或者输入【break】返回主目录");
							String idStr = sc.nextLine();
							if(idStr.equals("break")){
								break;
							}
							long id = Long.parseLong(idStr);
							teacher tea= sms.findById(id);
							if(tea == null){
								System.out.println("该教师不存在！");
								continue;
							}
							System.out.println(tea);
						}
						break;
					}
					case "5":{
						while(true){
							System.out.println("请输入要更改的教师的【id】或者输入【break】返回主目录");
							String idStr = sc.nextLine();
							if(idStr.equals("break")){
								break;
							}
							long id = Long.parseLong(idStr);
							teacher tea= sms.findById(id);
							if(tea == null){
								System.out.println("该教师不存在！");
								continue;
							}
							System.out.println("要修改的教师："+tea);
							 System.out.println("请输入教师的年龄#姓名或者break");
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
						System.out.println("bye bye!欢迎再次使用！");
						System.exit(0);
					}
					case "help":{
						sms.menu();
						break;
					}
					default:
						System.out.println("输入有误！");
				}
			}
		}
	}

