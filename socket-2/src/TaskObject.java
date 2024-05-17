import java.io.Serializable;

public class TaskObject implements Serializable , ITask{

    int number;
    String exec;
    int result;

public void setExecNumber(int x){ //クライアントで最初に計算させる数字を入力しておく関数
    this.number=x;
    }

public void exec() {
    // サーバで計算を実行する処理
    int maxPrime = 2; // 最低でも2は素数なので、最大素数の初期値は2としておく
    for (int i = 2; i <= number; i++) {
        if (isPrime(i)) {
            maxPrime = i;
        }
    }
    // 計算結果をresultフィールドに保存
    this.result = maxPrime;
}

public int getResult(){ //クライアントで結果を取り出す関数
return result;
}

// 素数判定メソッド
private boolean isPrime(int n) {
    if (n <= 1) {
        return false;
    }
    if (n <= 3) {
        return true;
    }
    if (n % 2 == 0 || n % 3 == 0) {
        return false;
    }
    for (int i = 5; i * i <= n; i += 6) {
        if (n % i == 0 || n % (i + 2) == 0) {
            return false;
        }
    }
    return true;
}
}
