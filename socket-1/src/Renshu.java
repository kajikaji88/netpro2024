public class Renshu {

// xを2倍にして返す関数
public int doubleValue(int x) {
    return x * 2;
}

//ここに続きを実装していく。

//	1から n までの整数の合計値を返す。
public int sumUpToN(int n){
    int sum=0;
    for(int i=1;i<=n;i++){
        sum=sum+i;
    }
    return sum;
}

//p から q までの整数の合計値を返す。
public int sumFromPtoQ(int p, int q){
    if(p>q){
        return -1;
    }

    int sum=0;
    for(int i=p;i<=q;i++){
        sum=sum+i;
    }
    return sum;
}

//配列 a[] の指定された index から以降の要素の合計値を返す。不正な index の場合は -1 を返す
public int sumFromArrayIndex(int[] a, int index){
    if(index<0 || index>=a.length){
        return -1;
    }
    int sum=0;
    for(int i=index;i<a.length;i++){
        sum=sum+a[i];
    }
    return sum;
}

//配列 a の中で最大の値を返す。
public int selectMaxValue(int[] a){
    int max=a[0];
    for(int i=0;i<a.length;i++){
        if(max<a[i]){
            max=a[i];
        }
    }
    return max;
}

//配列 a の中で最大の値が入っている要素の位置（index）を返す。最大値が複数の場合は最小のindexを返す。
public int selectMaxIndex(int[] a){
    int max=a[0];
    int index=0;
    for(int i=0;i<a.length;i++){
        if(max<a[i]){
            max=a[i];
            index=i;
        }
    }
    return index;
}

//配列 p の i 番目と j 番目の要素を入れ替える
public void swapArrayElements(int[] p, int i, int j){
    int temp =p[i];
    p[i]=p[j];
    p[j]=temp;
}

//同じ長さの二つの配列 a と b の内容を入れ替える
public boolean swapTwoArrays(int[] a, int[] b){
    if(a.length!=b.length){
        return false;
    }
    for(int i=0;i<a.length;i++){
        int temp=a[i];
        a[i]=b[i];
        b[i]=temp;
    }
    return true;
}

//バブルソート
public void bubbleSort(int []a){
    for(int i=a.length-1;i>0;i--){
        for(int j=0;j<i;j++){
            if(a[j]>a[j+1]){
                int box=a[j];
                a[j]=a[j+1];
                a[j+1]=box;
            }
        }
    }
}

//クイックソート
public void quickSort(int []a,int left,int right) {
    if (left>=right) {
        return;
    }
    int p = a[(left+right)/2];
    int l = left, r = right, tmp;
    while(l<=r) {
        while(a[l] < p) { l++; }
        while(a[r] > p) { r--; }
        if (l<=r) {
            tmp = a[l]; a[l] = a[r]; a[r] = tmp;
            l++; r--;
        }
    }
    quickSort(a,left,r);  // ピボットより左側をクイックソート
    quickSort(a,l,right); // ピボットより右側をクイックソート
}
public void quickSort(int[] a) {
    quickSort(a, 0, a.length - 1);
}
}