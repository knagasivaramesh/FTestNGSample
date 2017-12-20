package TaskApp;

import org.openqa.selenium.By;

import com.pages.KeywordUtil;


public class AndroidConstants {
			public static class TaskApplication{
			public static final By input_User=By.xpath("//android.widget.EditText[contains(@resource-id,'com.gabriel.testapplication:id/et_email')]");
			public static final By input_Pswd=By.xpath("//android.widget.EditText[contains(@resource-id,'com.gabriel.testapplication:id/et_password')]");
			public static final By btn_Login=By.xpath("//android.widget.Button[contains(@resource-id,'com.gabriel.testapplication:id/bt_login')]");
			public static final By btn_NewTask=By.xpath("//android.widget.ImageButton[contains(@resource-id,'com.gabriel.testapplication:id/fab')]");
			public static final By input_ActivityName=By.xpath("//android.widget.EditText[contains(@resource-id,'com.gabriel.testapplication:id/et_new_activ_name')]");
			public static final By input_Location=By.xpath("//android.widget.EditText[contains(@resource-id,'com.gabriel.testapplication:id/et_new_location')]");
			public static final By input_StartTime=By.xpath("//android.widget.EditText[contains(@resource-id,'com.gabriel.testapplication:id/et_new_start_time')]");
			public static final By input_ExpCount=By.xpath("//android.widget.EditText[contains(@resource-id,'com.gabriel.testapplication:id/et_new_exp_count')]");
			public static final By btn_AddTask=By.xpath("//android.widget.Button[contains(@resource-id,'com.gabriel.testapplication:id/bt_add_task')]");
			public static final By label_Activity=By.xpath("//android.widget.TextView[contains(@text,'Activity')]");
			public static final By label_Location=By.xpath("//android.widget.TextView[contains(@text,'Location')]");
			public static final By label_StartTime=By.xpath("//android.widget.TextView[contains(@text,'Strt Time')]");
			public static final By label_ExpCount=By.xpath("//android.widget.TextView[contains(@text,'Exp Count')]");
			public static final By label_ActiveTasks=By.xpath("//android.widget.TextView[contains(@text,'Active Tasks')]");
			public static final By label_CompletedTasks=By.xpath("//android.widget.TextView[contains(@text,'Completed Tasks')]");
			public static final By ActivityName=By.xpath("//android.widget.EditText[contains(@text,'"+KeywordUtil.GetValue("Taskactivityname")+"')]");
			public static final By Location=By.xpath("//android.widget.EditText[contains(@text,'"+KeywordUtil.GetValue("Tasklocation")+"')]");
			public static final By StartTime=By.xpath("//android.widget.EditText[contains(@text,'"+KeywordUtil.GetValue("Taskstarttime")+"')]");
			public static final By ExpCount=By.xpath("//android.widget.EditText[contains(@text,'"+KeywordUtil.GetValue("Taskexpcount")+"')]");
		}	
}
