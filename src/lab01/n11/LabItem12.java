package lab01.n11;

import android.graphics.Color;
import android.widget.TextView;

public class LabItem12 extends LabItem {

	public LabItem12(Lab01n11Activity main) {
		super(main);
		topic = "1.2 Метод прогонки";
	}
	
	@Override
	public void exec() {
		super.exec();
		scrInput();
	}	
	
	private void scrInput() {
		ll.removeAllViews();
		
        TextView tv = new TextView(ll.getContext());
        tv.setText(topic);
        tv.setTextColor(Color.YELLOW);
        ll.addView(tv);
	}

}