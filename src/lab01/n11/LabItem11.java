package lab01.n11;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class LabItem11 extends LabItem {
	private EditText etSize; // �����������
	private int m_size = 0;
	private TableLayout tl;
	//private LinearLayout ll;
	public static EditText[][] edt; // ������ �������

	public LabItem11(Lab01n11Activity main) {
		super(main);
		topic = "1.1 �������� LU - ���������� ������";
	}
	
	@Override
	public void exec() {
		super.exec();
		i11_scrInput();
	}
	
    ///////////////////////////////////////////////////////////////////////////
    // ������������ ������� ������ ����� �������� ������
    ///////////////////////////////////////////////////////////////////////////
    private void i11_scrInput() {
		ll.removeAllViews();
		
        TextView tv = new TextView(ll.getContext());
        tv.setText("1.1 �������� LU - ���������� ������");
        tv.setTextColor(Color.YELLOW);
        ll.addView(tv);
        
        LinearLayout ll1 = new LinearLayout(ll.getContext());
        ll1.setOrientation(LinearLayout.HORIZONTAL);
        ll.addView(ll1);
        
        tv = new TextView(ll.getContext());
        tv.setText("������� ����� ���������:");
        ll1.addView(tv);
        
        etSize = new EditText(ll.getContext());
        etSize.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
        etSize.setText("4");
        ll1.addView(etSize);
        
        Button bt = new Button(ll.getContext());
        bt.setText("�������!");
        bt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				createTable();
			}
		});
        
        ll1.addView(bt);
        
        tv = new TextView(ll.getContext());
        tv.setText("������� ������������ �������:");
        ll.addView(tv);
        
        tl = new TableLayout(ll.getContext());
        ll.addView(tl);
        
        createTable();
        
        ll1 = new LinearLayout(ll.getContext());
        ll1.setOrientation(LinearLayout.HORIZONTAL);
        ll1.setLayoutParams(new LinearLayout.LayoutParams(
    	          LinearLayout.LayoutParams.FILL_PARENT,
    	          LinearLayout.LayoutParams.FILL_PARENT
    	      ));       
        ll.addView(ll1);
        
        bt = new Button(ll.getContext());
        bt.setText("������ �������");
        LinearLayout.LayoutParams l2 = new LinearLayout.LayoutParams(
  	          LinearLayout.LayoutParams.WRAP_CONTENT,
  	          LinearLayout.LayoutParams.WRAP_CONTENT
  	      );
        l2.gravity = Gravity.BOTTOM;
        bt.setLayoutParams(l2);
        ll1.addView(bt);
        
        bt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				i11_scrResult();
			}
		});
    	
        bt = new Button(ll.getContext());
        bt.setText("���������");        
        bt.setLayoutParams(l2);
        ll1.addView(bt);
        
        bt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				main.appClose();
			}
		});
    }	

    ///////////////////////////////////////////////////////////////////////////
    // ������������ ������� �� ������
    ///////////////////////////////////////////////////////////////////////////
    private void createTable() {
    	m_size = Integer.valueOf( etSize.getText().toString() );
        edt = new EditText[m_size][m_size+1]; // ��������� ������� �������
    	
    	tl.removeAllViews();
        
        for (int r = 0; r < m_size; r++) {
        	TableRow tr = new TableRow(context);
        	EditText e;
            for (int c = 0; c < m_size; c++) {
            	e = new EditText(context);
            	e.setText("0");
            	tr.addView(e);
            	edt[r][c] = e;
            }
            TextView t = new TextView(context);
            t.setText("=");
        	tr.addView(t);
        	
        	e = new EditText(context);
        	e.setText("0");
        	tr.addView(e);
        	
        	edt[r][m_size] = e;
        	
            tl.addView(tr);
        }
        
        if (m_size == 4)
        	init4x5();
        
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // ������������� �������� ������
    ///////////////////////////////////////////////////////////////////////////
    private void init4x5() {
    	edt[0][0].setText("-1");
    	edt[0][1].setText("-3");
    	edt[0][2].setText("-4");
    	edt[0][3].setText("0");
    	edt[0][4].setText("-3");
        
    	edt[1][0].setText("3");
    	edt[1][1].setText("7");
    	edt[1][2].setText("-8");
    	edt[1][3].setText("3");
    	edt[1][4].setText("30");
        
    	edt[2][0].setText("1");
    	edt[2][1].setText("-6");
    	edt[2][2].setText("2");
    	edt[2][3].setText("5");
    	edt[2][4].setText("-90");
        
    	edt[3][0].setText("-8");
    	edt[3][1].setText("-4");
    	edt[3][2].setText("-1");
    	edt[3][3].setText("-1");
    	edt[3][4].setText("12");    
    }

    ///////////////////////////////////////////////////////////////////////////
    // ������������ ������ �����������
    ///////////////////////////////////////////////////////////////////////////
    private void i11_scrResult() {
    	// �� �����������
    	double a[][] = new double[m_size][m_size];
    	for (int r = 0; r < m_size; r++)
        	for (int c = 0; c < m_size; c++)
        		a[r][c] = Double.valueOf(edt[r][c].getText().toString());
        
    	//double [][] at = Determinant.matrTransp(a); 
    	double mdet = Determinant.det(a);
    	
    	double a2[][] = new double[m_size][m_size+1];
    	for (int r = 0; r < m_size; r++)
        	for (int c = 0; c < m_size+1; c++)
        		a2[r][c] = Double.valueOf(edt[r][c].getText().toString());
    	
    	// ��������� ����� �����������
		ll.removeAllViews();
		
		/*
		Spanned spanText = android.text.Html.fromHtml("<u>My</u> <i>some</i> <b>bold</b> text"); 
		TextView t = new TextView(this);
		t.setText(spanText);
		ll.addView(t);
		*/
		
		String rs = "<?xml version='1.0' encoding='UTF-8' ?>"
				+ "<u><b>1.1 �������� LU - ���������� ������<b><u><br>"
				+ "<u><b>�������:</b></u>"
				+ "<table>"
				;
				
		
		for (int r = 0; r < m_size; r++) {
			String sign;
			rs += "<tr>";
			for (int c = 1; c <= m_size; c++) {
				double v = a2[r][c-1];
				if (v < 0)
					sign = "	- ";
				else if (c != 1)
					sign = "+ ";
				else
					sign = "";
				if (v == 0.0)
					rs += "<td></td>";
				else if (v == 1.0 || v == -1.0)
					rs += "<td align=right>" + sign 
					+ "<i>x</i><font size=1>" + c + "</font>";
				else
					rs += "<td align=right>" + sign + "<font color=blue>" + Math.abs(v) 
						+ "</font><i>x</i><font size=1>" + c + "</font>";
			}
			
			rs += "<td>=<td align=right><font color=blue>" + a2[r][m_size] + "</font>";
		}
		
		rs += "</table>";
		rs += "<u><b>�������:<b></u>";
				
    	calcGaus(a2);

    	rs += "<table>";
		rs += "<tr><td><i>det</i><td>=<td align=right><font color=blue>" + String.format("%.2f", mdet) + "</font>";
    	
		for (int i = 1; i <= m_size; i++) {
			rs += "<tr><td align=right><i>x</i><font size=1>" + i + "</font><td>="
					+ "<td align=right><font color=blue>" + String.format("%.2f", a2[i-1][m_size]) + "</font>"; 
		}
		
		rs += "</table>";
		
		WebView wv = new WebView(context);
		wv.loadData(rs,"text/html", "utf-8");
		ll.addView(wv);
		
        LinearLayout ll1 = new LinearLayout(context);
        ll1.setOrientation(LinearLayout.HORIZONTAL);
        ll1.setLayoutParams(new LinearLayout.LayoutParams(
  	          LinearLayout.LayoutParams.FILL_PARENT,
  	          LinearLayout.LayoutParams.FILL_PARENT
  	      ));               
        ll.addView(ll1);
        
		Button bt = new Button(context);
		bt.setText("� ������");
        LinearLayout.LayoutParams l2 = new LinearLayout.LayoutParams(
    	          LinearLayout.LayoutParams.WRAP_CONTENT,
    	          LinearLayout.LayoutParams.WRAP_CONTENT
    	      );
        l2.gravity = Gravity.BOTTOM;
        bt.setLayoutParams(l2);
		ll1.addView(bt);
		
		bt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				i11_scrInput();
			}
		});
		
        bt = new Button(context);
        bt.setText("���������");        
        bt.setLayoutParams(l2);
        ll1.addView(bt);
        
        bt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				main.appClose();
			}
		});
    }
    
    private void calcGaus(double [][] a) {
    	int dim = a.length;
    	
    	// ������ ������ ����
    	for (int R = 0; R < dim; R++) {
    		for (int r = R+1; r < dim; r++) {
    			double cr = a[r][R]/a[R][R];
    			for (int c = 0; c <= dim; c++) {
    				a[r][c] = a[r][c] - a[R][c] * cr;
    			}
    		}
    	}

    	// ������ ����� �����
    	for (int R = dim-1; 0 <= R; R--) {
    		for (int r = R-1; 0 <= r; r--) {
    			double cr = a[r][R]/a[R][R];
    			for (int c = 0; c <= dim; c++) {
    				a[r][c] = a[r][c] - a[R][c] * cr;
    			}
    		}
    	}
    	
    	// �������� � "1"
    	for (int r = 0; r < dim; r++) {
    		if (a[r][r] == 1.0) continue;
    		a[r][dim] = a[r][dim]/a[r][r];  
    		a[r][r] = 1.0;
    	}
    }    
    
}
