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
static final int m=8,Q=9;//m为行数，Q为数字大小
AudioClip a,b,c ;
URL url,urltwo,urlthree;
JFrame myFrame; //主面板 
Container container; 
JPanel southPanel,northPanel; //子面板 ss
JButton button[][] = new JButton[m][m];//游戏按钮数组 
ImageIcon[][] picture = new ImageIcon[m][m];
JButton ernButton[]=new JButton[4]; //退出，重列，重新开始按钮 
JButton first,second; //分别记录两次被选中的按钮 
int grid[][] = new int[11][10];//储存游戏按钮位置 
static boolean pressInformation=false; //判断是否有按钮被选中 
int x0=0,y0=0,x=0,y=0,fristMsg=0,secondMsg=0; //游戏按钮的位置坐标 
int i,j,k,n;//消除方法控制

public void init(){ 
myFrame=new JFrame("连连看");
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
ernButton[i]=new JButton("退出");
if(i==1)
ernButton[i]=new JButton("重列"); 
if(i==2)
ernButton[i]=new JButton("下一关");
if(i==3)
ernButton[3]=new JButton("开启背景音乐");
ernButton[i].addActionListener(this);
northPanel.add(ernButton[i]);
}
myFrame.setBounds(280,0,700,750); //设置窗口大小
myFrame.setVisible(true);
} 
public void randomBuild() { 
int randoms,i,j; 
for(int twins=1;twins<=(m*m/2);twins++) { //创建(m*m)/2个数
randoms=(int)(Math.random()*Q+1); //任选0~Q间的数
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
JOptionPane.showMessageDialog(null, "开始游戏？");
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
pressInformation=false; //这里一定要将按钮点击信息归为初始 
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
eliminate(); //消除
buttontext();
} 
}
}

public void eliminate() { //相同的情况下能不能消去。仔细分析，不一条条注释 
if((x0==x &&(y0==y+1||y0==y-1)) || ((x0==x+1||x0==x-1)&&(y0==y))){ //判断是否相邻 
remove();
} 
else{ 
for (j=0;j<9;j++ ) { 
if (grid[x0][j]==0){ //判断第一个按钮同行哪个按钮为空 
if (y>j) { //如果第二个按钮的Y坐标大于空按钮的Y坐标说明第一按钮在第二按钮左边 
for (i=y-1;i>=j;i-- ){ //判断第二按钮左侧直到第一按钮中间有没有按钮 
if (grid[x][i]!=0) { 
k=0; 
break; 
} 
else{ k=1; } //K=1说明通过了第一次验证 
} 

if (k==1) { 
linePassOne(); 
} 
} 
if (y<j){ //如果第二个按钮的Y坐标小于空按钮的Y坐标说明第一按钮在第二按钮右边 
for (i=y+1;i<=j ;i++ ){ //判断第二按钮左侧直到第一按钮中间有没有按钮 
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
for (i=0;i<11;i++ ) { //列 
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
if (y0>j){ //第一按钮同行空按钮在左边 
for (i=y0-1;i>=j ;i-- ){ //判断第一按钮同左侧空按钮之间有没按钮 
if (grid[x0][i]!=0) { 
k=0; 
break; 
} 
else { k=2; } //K=2说明通过了第二次验证 
} 
} 
if (y0<j){ //第一按钮同行空按钮在与第二按钮之间 
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
	  urltwo = getClass().getResource("汤晶锦 - 鹿港小镇.wav");
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
public void actionPerformed(ActionEvent e) {//监听器
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