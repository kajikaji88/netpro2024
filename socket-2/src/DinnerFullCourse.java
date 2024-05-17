public class DinnerFullCourse {

	private Dish[] list = new Dish[5];// [0]-[4]の計5個

	public static void main(String[] args) {

		DinnerFullCourse fullcourse = new DinnerFullCourse();
		fullcourse.eatAll();
	}

	DinnerFullCourse() {
		list[0]=new Dish();
		list[0].setName("特選シーザサラダ");
		list[0].setValune(10);

		list[1]=new Dish();
		list[1].setName("銀しゃり");
		list[1].setValune(2);

		list[2]=new Dish();
		list[2].setName("梅干し");
		list[2].setValune(20);

		list[3]=new Dish();
		list[3].setName("すき焼き");
		list[3].setValune(100);

		list[4]=new Dish();
		list[4].setName("ジェラート");
		list[4].setValune(15);
	}

	void eatAll(){
		String str="";
		for(Dish element:list){
			str+=element.getName()+"="+element.getValune()+">";

		}

		System.out.println("たかしへ、ママです。今日の晩御飯は" + str + "よ");

	}
}