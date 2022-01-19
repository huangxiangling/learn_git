package cn.com.sheep.workspace.Abstractdemo;

public class Wine2Test {

    public static void main(String[] args) {
        Wine2[] wine2s = new Wine2[2];
        JNC2 jnc2 = new JNC2();
        JGJ2 jgj2 = new JGJ2();
        wine2s[0] = jnc2;
        wine2s[1] = jgj2;
        for (int i = 0; i < wine2s.length; i++) {

            System.out.println(wine2s[i].toString() + "--" + wine2s[i].drink());
        }
    }
}
