package lianlian;
import java.awt.*; 
import java.awt.event.*; 
import java.applet.Applet;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.net.URL;
import java.applet.AudioClip;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.*;
public class A extends Applet implements ActionListener
{
static final int m=8,Q=9;//mΪ������QΪ���ִ�С
AudioClip a,b,c ;
URL url,urltwo,urlthree;
JFrame myFrame; //����� 
Container container; 
JPanel southPanel,northPanel; //����� ss
JButton button[][] = new JButton[m][m];//��Ϸ��ť���� 
ImageIcon[][] picture = new ImageIcon[m][m];
JButton ernButton[]=new JButton[4]; //�˳������У����¿�ʼ��ť 
JButton first,second; //�ֱ��¼���α�ѡ�еİ�ť 
int grid[][] = new int[11][10];//������Ϸ��ťλ�� 
static boolean pressInformation=false; //�ж��Ƿ��а�ť��ѡ�� 
int x0=0,y0=0,x=0,y=0,fristMsg=0,secondMsg=0; //��Ϸ��ť��λ������ 
int i,j,k,n;//������������

public void init(){ 
myFrame=new JFrame("������");
container = myFrame.getContentPane();
container.setLayout(new BorderLayout()); 
southPanel=new JPanel();
northPanel=new JPanel();
container.add(southPanel,"Center"); 
container.add(northPanel,"North");
southPanel.setLayout(new GridLayout(m,m));
southPanel.setFont(new Font("helevetica",Font.ITALIC,24));
for(int i= 0;i< m;i++){ 
for(int j=0;j < m;j++ ){
	 String st = "F:/news/news3/lianlian/images//" + (grid[i+1][j+1]) +".jpg" ; 
	 picture[i][j] = new ImageIcon(st); 
	button[i][j] = new JButton(picture[i][j]); 
	 button[i][j].setSize(31,34);
     button[i][j].addActionListener(this); 
     southPanel.add(button[i][j]);                        
} 
}
northPanel.setLayout(new GridLayout(0,4));
northPanel.setFont(new Font("helevetica",Font.ITALIC,30));
for(int i=0;i<4;i++){
if(i==0)
ernButton[i]=new JButton("�˳�");
if(i==1)
ernButton[i]=new JButton("����"); 
if(i==2)
ernButton[i]=new JButton("��һ��");
if(i==3)
ernButton[3]=new JButton("������������");
ernButton[i].addActionListener(this);
northPanel.add(ernButton[i]);
}
myFrame.setBounds(280,0,700,750); //���ô��ڴ�С
myFrame.setVisible(true);
} 
public void randomBuild() { 
int randoms,i,j; 
for(int twins=1;twins<=(m*m/2);twins++) { //����(m*m)/2����
randoms=(int)(Math.random()*Q+1); //��ѡ0~Q�����
for(int alike=1;alike<=2;alike++) { 
i=(int)(Math.random()*m+1); 
j=(int)(Math.random()*m+1); 
while(grid[i][j]!=0) { 
i=(int)(Math.random()*m+1); 
j=(int)(Math.random()*m+1); 
} 
this.grid[i][j]=randoms; 
} 
}
JOptionPane.showMessageDialog(null, "��ʼ��Ϸ��");
} 
public void reload() { 
int save[] = new int[64]; 
int n=0,c,r; 
int grid[][]= new int[10][9];
for(int i=0;i<=m;i++) { 
for(int j=0;j<=m;j++) { 
if(this.grid[i][j]!=0) { 
save[n]=this.grid[i][j]; 
n++; } } } 
n=n-1; 
this.grid=grid; 
while(n>=0) { 
c=(int)(Math.random()*m+1); 
r=(int)(Math.random()*m+1); 
while(grid[c][r]!=0) { 
c=(int)(Math.random()*m+1); 
r=(int)(Math.random()*m+1); 
} 
this.grid[c][r]=save[n]; 
n--; 
} 
myFrame.setVisible(false); 
pressInformation=false; //����һ��Ҫ����ť�����Ϣ��Ϊ��ʼ 
init(); 
for(int i = 0;i < m;i++){ 
for(int j = 0;j < m;j++ ){ 
if(grid[i+1][j+1]==0) 
button[i][j].setVisible(false); } } } 
public void estimateEven(int placeX,int placeY,JButton bz) { 
if(pressInformation==false) { 
x=placeX; 
y=placeY; 
secondMsg=grid[x][y]; 
second=bz; 
pressInformation=true; 
} 
else { 
x0=x; 
y0=y; 
fristMsg=secondMsg; 
first=second; 
x=placeX; 
y=placeY; 
secondMsg=grid[x][y]; 
second=bz; 
if(fristMsg==secondMsg && second!=first){ 
eliminate(); //����
buttontext();
} 
}
}

public void eliminate() { //��ͬ��������ܲ�����ȥ����ϸ��������һ����ע�� 
if((x0==x &&(y0==y+1||y0==y-1)) || ((x0==x+1||x0==x-1)&&(y0==y))){ //�ж��Ƿ����� 
remove();
} 
else{ 
for (j=0;j<9;j++ ) { 
if (grid[x0][j]==0){ //�жϵ�һ����ťͬ���ĸ���ťΪ�� 
if (y>j) { //����ڶ�����ť��Y������ڿհ�ť��Y����˵����һ��ť�ڵڶ���ť��� 
for (i=y-1;i>=j;i-- ){ //�жϵڶ���ť���ֱ����һ��ť�м���û�а�ť 
if (grid[x][i]!=0) { 
k=0; 
break; 
} 
else{ k=1; } //K=1˵��ͨ���˵�һ����֤ 
} 

if (k==1) { 
linePassOne(); 
} 
} 
if (y<j){ //����ڶ�����ť��Y����С�ڿհ�ť��Y����˵����һ��ť�ڵڶ���ť�ұ� 
for (i=y+1;i<=j ;i++ ){ //�жϵڶ���ť���ֱ����һ��ť�м���û�а�ť 
if (grid[x][i]!=0){ 
k=0; 
break; 
} 
else { k=1; } 
} 
if (k==1){ 
linePassOne(); 
} 
} 
if (y==j ) { 
linePassOne(); 
} 
} 
if (k==2) { 
if (x0==x) { 
remove(); 
} 
if (x0<x) { 
for (n=x0;n<=x-1;n++ ) { 
if (grid[n][j]!=0) { 
k=0; 
break; 
} 
if(grid[n][j]==0 && n==x-1) { 
remove();
} 
} 
} 
if (x0>x) { 
for (n=x0;n>=x+1 ;n-- ) { 
if (grid[n][j]!=0) { 
k=0; 
break; 
} 
if(grid[n][j]==0 && n==x+1) { 
remove();
} 
} 
} 
} 
} 
for (i=0;i<11;i++ ) { //�� 
if (grid[i][y0]==0) { 
if (x>i) { 
for (j=x-1;j>=i ;j-- ) { 
if (grid[j][y]!=0) { 
k=0; 
break; 
} 
else { k=1; } 
} 
if (k==1) { 
rowPassOne(); 
} 
} 
if (x<i) { 
for (j=x+1;j<=i;j++ ) { 
if (grid[j][y]!=0) { 
k=0; 
break; 
} 
else { k=1; } 
} 
if (k==1) { 
rowPassOne(); 
} 
} 
if (x==i) { 
rowPassOne(); 
} 
} 
if (k==2){ 
if (y0==y) { 
remove();
} 
if (y0<y) { 
for (n=y0;n<=y-1 ;n++ ) { 
if (grid[i][n]!=0) { 
k=0; 
break; 
} 
if(grid[i][n]==0 && n==y-1) { 
remove();

} 
} 
} 
if (y0>y) { 
for (n=y0;n>=y+1 ;n--) { 
if (grid[i][n]!=0) { 
k=0; 
break; 
} 
if(grid[i][n]==0 && n==y+1) { 
remove();
}
} 
} 
} 
} 
} 
} 
public void linePassOne(){ 
if (y0>j){ //��һ��ťͬ�пհ�ť����� 
for (i=y0-1;i>=j ;i-- ){ //�жϵ�һ��ťͬ���հ�ť֮����û��ť 
if (grid[x0][i]!=0) { 
k=0; 
break; 
} 
else { k=2; } //K=2˵��ͨ���˵ڶ�����֤ 
} 
} 
if (y0<j){ //��һ��ťͬ�пհ�ť����ڶ���ť֮�� 
for (i=y0+1;i<=j ;i++){ 
if (grid[x0][i]!=0) { 
k=0; 
break; 
} 
else{ k=2; } 
} 
} 
} 
public void rowPassOne(){ 
if (x0>i) { 
for (j=x0-1;j>=i ;j-- ) { 
if (grid[j][y0]!=0) { 
k=0; 
break; 
} 
else { k=2; } 
} 
} 
if (x0<i) { 
for (j=x0+1;j<=i ;j++ ) { 
if (grid[j][y0]!=0) { 
k=0; 
break; 
} 
else { k=2; } 
} 
} 
} 
public void remove(){
first.setVisible(false); 
second.setVisible(false); 
pressInformation=false; 
grid[x0][y0]=0; 
grid[x][y]=0;
removeshound();
}  
public void Sound(){
	  urltwo = getClass().getResource("������ - ¹��С��.wav");
	  b= java.applet.Applet.newAudioClip(urltwo);
	  b.play();
	 }
public void clickSound(){
	urlthree = getClass().getResource("BOMB1.wav");
	  c = java.applet.Applet.newAudioClip(urlthree);
	  c.play();
}
public void removeshound(){
	url = getClass().getResource("TORPEDOS.wav");
	  a = java.applet.Applet.newAudioClip(url);
	  a.play();
}
public void buttontext(){
	int c,r,n=0;
	for(c=0;c<11;c++){
		for(r=0;r<10;r++){
			if(grid[c][r]!=0)
				n++;
		}
	}
		}
public void actionPerformed(ActionEvent e) {//������
if(e.getSource()==ernButton[2]){ 
int grid[][] = new int[11][10]; 
this.grid = grid; 
randomBuild(); 
myFrame.setVisible(false); 
pressInformation=false; 
init(); 
}
if(e.getSource()==ernButton[3])
	Sound();
if(e.getSource()==ernButton[0]) 
System.exit(0); 
if(e.getSource()==ernButton[1])
reload();
for(int c=0;c<m;c++){ 
for(int r=0;r<m;r++){ 
	if(e.getSource()==button[c][r]){ 
	clickSound();
    estimateEven(c+1,r+1,button[c][r]);
	}
} 
}
}
public static void main(String[] args){
A llk = new A();
llk.randomBuild();
llk.init();
} 
}