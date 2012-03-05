package lab01.n11;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.Menu;

public class Lab01n11Activity extends Activity {
	private LabItem labItem;
	public LinearLayout ll;
	public static EditText[][] edt; // ������ �������
	
	private static final int IDM_CLOSE = 101;
	private static final int IDM_ABOUT = 102;
	private static final int IDM_L11 = 111;
	private static final int IDM_L12 = 112;
	private static final int IDM_L13 = 113;
	private static final int IDM_L14 = 114;
	private static final int IDM_L15 = 115;
	
	private boolean started = false;

	// ����������� ������
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ll = (LinearLayout)findViewById(R.id.ll0);
        registerForContextMenu(ll);
    }
    
    
    public void onWindowFocusChanged(boolean b) {
    	if (!started) {
    		openContextMenu(ll);
    		started = true;
    	}
    }
    
    // ���������� ����
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(Menu.NONE, IDM_CLOSE, Menu.NONE, "�������");
    	menu.add(Menu.NONE, IDM_ABOUT, Menu.NONE, "� ���������");
    	return true;
    }
    
    // ���������� ������ ����
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case IDM_CLOSE:
            	appClose();
            	
                return true;
            case IDM_ABOUT:
            	new AlertDialog.Builder(this)
            	.setTitle("� ���������")
            	.setMessage("�����������: �������� �.�.")
            	.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						return;
					}
				})
            	.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }	
    }
    
    // ����������� ����
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
    	menu.add(Menu.NONE, IDM_L11, Menu.NONE, "1.1 �������� LU");
    	menu.add(Menu.NONE, IDM_L12, Menu.NONE, "1.2 ����� ��������");
    	menu.add(Menu.NONE, IDM_L13, Menu.NONE, "1.3 ����� ������� ��������");
    	menu.add(Menu.NONE, IDM_L14, Menu.NONE, "1.4 ����� ��������");
    	menu.add(Menu.NONE, IDM_L15, Menu.NONE, "1.5 �������� QR");
    }
    
    // ���������� ����������� ����
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case IDM_L11:
			labItem = new LabItem11(this);
			break;
		case IDM_L12:
			labItem = new LabItem12(this);
			break;
    	default:
    		return super.onContextItemSelected(item);
    	}
    	
		labItem.exec();
    	return true;
    }
    
    //////////////////////////////////////////////////////////////////////////
    public void appClose() {
    	new AlertDialog.Builder(this)
    	.setTitle("����� �� ����������")
    	.setMessage("������ ����� �� ����������?")
    	.setPositiveButton("��", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
            	finish();
			}
		})
    	.setNegativeButton("���", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
            	return;
			}
		})
    	.show();
    }
      
}