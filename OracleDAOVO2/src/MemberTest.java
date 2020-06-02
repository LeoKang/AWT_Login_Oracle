import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MemberTest {
	static MemberDAO dao;
	static Frame f;
	static TextField name, sal, tfMsg;
	static Button btn;

	public static void main(String[] args) {
		dao = new MemberDAO();
		f = new Frame("���������ȸ���α׷�");
		f.setSize(460, 100);
		f.setLayout(new FlowLayout());
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		Label lid = new Label("������ : ", Label.RIGHT);
		Label lpwd = new Label("�޿� : ", Label.RIGHT);
		name = new TextField(10);
		sal = new TextField(10);
		tfMsg = new TextField(40);
		tfMsg.setEditable(false);
//		sal.setEchoChar('*');
		btn = new Button("��ȸ");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sal.setText("");
				tfMsg.setText("");
				System.out.println(name.getText());
				System.out.println(sal.getText());

				if (name.getText().equals("")) {
					tfMsg.setText("������� �Է��ϼ���.");
				} else {
					ArrayList<MemberVo> list = dao.list(name.getText());

					if(list.size() == 0) {
						tfMsg.setText("������� ��ġ�ϴ� ����� �����ϴ�.");
					}else {
						tfMsg.setText("��ȸ�� �Ǿ����ϴ�.");
					}
					
					for (int i = 0; i < list.size(); i++) {
						MemberVo data = (MemberVo) list.get(i);
						String empno = data.getEmpno();
						String ename = data.getEname();
						int iSal = data.getSal();
						sal.setText(Integer.toString(iSal));

						System.out.println(empno + " : " + ename + " : " + sal);
					}
				}
			}
		});

		f.add(lid);
		f.add(name);
		f.add(lpwd);
		f.add(sal);
		f.add(btn);
		f.add(tfMsg);
		f.setVisible(true);
	}
}