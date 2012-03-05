package lab01.n11;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.Menu;

public class Lab01n11Activity extends Activity {
	private LabItem labItem;
	private int m_size = 0;
	private EditText etSize;
	private TableLayout tl;
	public LinearLayout ll;
	public static EditText[][] edt; // массив матрица
	
	private static final int IDM_CLOSE = 101;
	private static final int IDM_ABOUT = 102;
	private static final int IDM_L11 = 111;
	private static final int IDM_L12 = 112;
	private static final int IDM_L13 = 113;
	private static final int IDM_L14 = 114;
	private static final int IDM_L15 = 115;

	// Конструктор класса
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ll = (LinearLayout)findViewById(R.id.ll0);
        registerForContextMenu(ll);
        
       // i11_scrInput();
    }
    
    // Определить меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(Menu.NONE, IDM_CLOSE, Menu.NONE, "Закрыть");
    	menu.add(Menu.NONE, IDM_ABOUT, Menu.NONE, "О программе");
    	return true;
    }
    
    // Обработчик выбора меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case IDM_CLOSE:
            	appClose();
            	
                return true;
            case IDM_ABOUT:
            	new AlertDialog.Builder(this)
            	.setTitle("О программе")
            	.setMessage("Разработчик: Самодова Ю.А.")
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
    
    // Контекстное меню
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
    	menu.add(Menu.NONE, IDM_L11, Menu.NONE, "1.1 Алгоритм LU");
    	menu.add(Menu.NONE, IDM_L12, Menu.NONE, "1.2 Метод прогонки");
    	menu.add(Menu.NONE, IDM_L13, Menu.NONE, "1.3 Метод протых итераций");
    	menu.add(Menu.NONE, IDM_L14, Menu.NONE, "1.4 Метод вращения");
    	menu.add(Menu.NONE, IDM_L15, Menu.NONE, "1.5 Алгоритм QR");
    }
    
    // Обработчик контектного меню
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
    
    ///////////////////////////////////////////////////////////////////////////
    // Формирование первого экрана ввода исходных данных
    ///////////////////////////////////////////////////////////////////////////
    private void i11_scrInput() {
		ll.removeAllViews();
		
        TextView tv = new TextView(this);
        tv.setText("1.1 Алгоритм LU - разложение матриц");
        tv.setTextColor(Color.YELLOW);
        ll.addView(tv);
        
        LinearLayout ll1 = new LinearLayout(this);
        ll1.setOrientation(LinearLayout.HORIZONTAL);
        ll.addView(ll1);
        
        tv = new TextView(this);
        tv.setText("Введите число уравнений:");
        ll1.addView(tv);
        
        etSize = new EditText(this);
        etSize.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
        etSize.setText("4");
        ll1.addView(etSize);
        
        Button bt = new Button(this);
        bt.setText("Поехали!");
        bt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				createTable();
			}
		});
        
        ll1.addView(bt);
        
        tv = new TextView(this);
        tv.setText("Введите коэффициенты системы:");
        ll.addView(tv);
        
        tl = new TableLayout(this);
        ll.addView(tl);
        
        createTable();
        
        ll1 = new LinearLayout(this);
        ll1.setOrientation(LinearLayout.HORIZONTAL);
        ll1.setLayoutParams(new LinearLayout.LayoutParams(
    	          LinearLayout.LayoutParams.FILL_PARENT,
    	          LinearLayout.LayoutParams.FILL_PARENT
    	      ));       
        ll.addView(ll1);
        
        bt = new Button(this);
        bt.setText("Решить систему");
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
    	
        bt = new Button(this);
        bt.setText("Завершить");        
        bt.setLayoutParams(l2);
        ll1.addView(bt);
        
        bt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				appClose();
			}
		});
    	
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // Формирование таблицы на экране
    ///////////////////////////////////////////////////////////////////////////
    private void createTable() {
    	m_size = Integer.valueOf( etSize.getText().toString() );
        edt = new EditText[m_size][m_size+1]; // инициация размера массива
    	
    	tl.removeAllViews();
        
        for (int r = 0; r < m_size; r++) {
        	TableRow tr = new TableRow(this);
        	EditText e;
            for (int c = 0; c < m_size; c++) {
            	e = new EditText(this);
            	e.setText("0");
            	tr.addView(e);
            	edt[r][c] = e;
            }
            TextView t = new TextView(this);
            t.setText("=");
        	tr.addView(t);
        	
        	e = new EditText(this);
        	e.setText("0");
        	tr.addView(e);
        	
        	edt[r][m_size] = e;
        	
            tl.addView(tr);
        }
        
        if (m_size == 4)
        	init4x5();
        
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // Инициализация исходных данных
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
    
    //////////////////////////////////////////////////////////////////////////
    public void appClose() {
    	new AlertDialog.Builder(this)
    	.setTitle("Выход из приложения")
    	.setMessage("Хотите выйти из приложения?")
    	.setPositiveButton("Да", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
            	finish();
			}
		})
    	.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
            	return;
			}
		})
    	.show();
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // Формирование экрана результатов
    ///////////////////////////////////////////////////////////////////////////
    private void i11_scrResult() {
    	// Всё расчитываем
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
    	
    	// Формируем экран результатов
		ll.removeAllViews();
		
		/*
		Spanned spanText = android.text.Html.fromHtml("<u>My</u> <i>some</i> <b>bold</b> text"); 
		TextView t = new TextView(this);
		t.setText(spanText);
		ll.addView(t);
		*/
		
		String rs = "<?xml version='1.0' encoding='UTF-8' ?>"
				+ "<u><b>1.1 Алгоритм LU - разложения матриц<b><u><br>"
				+ "<u><b>Условие:</b></u>"
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
		rs += "<u><b>Решение:<b></u>";
				
    	calcGaus(a2);

    	rs += "<table>";
		rs += "<tr><td><i>det</i><td>=<td align=right><font color=blue>" + String.format("%.2f", mdet) + "</font>";
    	
		for (int i = 1; i <= m_size; i++) {
			rs += "<tr><td align=right><i>x</i><font size=1>" + i + "</font><td>="
					+ "<td align=right><font color=blue>" + String.format("%.2f", a2[i-1][m_size]) + "</font>"; 
		}
		
		rs += "</table>";
		
		WebView wv = new WebView(this);
		wv.loadData(rs,"text/html", "utf-8");
		ll.addView(wv);
		
        LinearLayout ll1 = new LinearLayout(this);
        ll1.setOrientation(LinearLayout.HORIZONTAL);
        ll1.setLayoutParams(new LinearLayout.LayoutParams(
  	          LinearLayout.LayoutParams.FILL_PARENT,
  	          LinearLayout.LayoutParams.FILL_PARENT
  	      ));               
        ll.addView(ll1);
        
		Button bt = new Button(this);
		bt.setText("В начало");
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
		
        bt = new Button(this);
        bt.setText("Завершить");        
        bt.setLayoutParams(l2);
        ll1.addView(bt);
        
        bt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				appClose();
			}
		});
    }
    
    private void calcGaus(double [][] a) {
    	int dim = a.length;
    	
    	// Проход сверху вниз
    	for (int R = 0; R < dim; R++) {
    		for (int r = R+1; r < dim; r++) {
    			double cr = a[r][R]/a[R][R];
    			for (int c = 0; c <= dim; c++) {
    				a[r][c] = a[r][c] - a[R][c] * cr;
    			}
    		}
    	}

    	// Проход снизу вверх
    	for (int R = dim-1; 0 <= R; R--) {
    		for (int r = R-1; 0 <= r; r--) {
    			double cr = a[r][R]/a[R][R];
    			for (int c = 0; c <= dim; c++) {
    				a[r][c] = a[r][c] - a[R][c] * cr;
    			}
    		}
    	}
    	
    	// Приводим к "1"
    	for (int r = 0; r < dim; r++) {
    		if (a[r][r] == 1.0) continue;
    		a[r][dim] = a[r][dim]/a[r][r];  
    		a[r][r] = 1.0;
    	}
    }
   
}