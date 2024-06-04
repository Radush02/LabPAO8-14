### Task 1

```java
class A {
    public static int a = 0;
}

class B extends A {
    public static int a = 5;
}

public class T1 {
    public static void main(String[] args) {
        A a = new B();
        a.a++;
        System.out.println(A.a);
        System.out.println(B.a);
    }
}
```
Output:
```
1
5
```
#### Explicatie:
* A a = new B() - Se creeaza o instanta a clasei B, dar referinta e de tip A
* a.a++ - Se incrementeaza a-ul din clasa A pt ca a este de tip A
* A.a - afiseaza 1
* B.b - afiseaza 5 pt ca a-ul din clasa B nu a fost modificat

Campurile statice A.a si B.a sunt independente, chiar daca B extinde A.


### Task 2

```java
class C {
    public C() {
        System.out.println("C");
    }

    public C(int x) {
        System.out.println("C " + x);
    }
}

class D extends C {
    public D() {
        super(1);
        System.out.println("D");
    }

    public D(int x) {
        System.out.println("D " + x);
    }
}

class E extends D {
    public E() {
        super(1);
        System.out.println("E");
    }
}

public class T2 {
    public static void main(String[] args) {
        new E();
    }
}
```
Output:
```
C 
D 1
E
```
#### Explicatie:
1) Cream instanta clasei E
2)  Constructorul E() apeleaza super(1) - se apeleaza constructorul D cu parametru 1
    *  super(1) se refera la D(int x), nu la D()
3) Constructorul D(int x) nu apeleaza super() cu un parametru explicit, deci se apeleaza constructorul implicit din C (adica super()).
4) Constructorul C() afiseaza "C"
5) Constructorul D(int x) afiseaza "D 1"
6) Constructorul E() afiseaza "E"

### Task 3

```java
class F {
    public int a = 0;
}

public class T3 {
    public static void main(String[] args) {
        F f = new F();
        bar(f);
        System.out.println(f.a);
        System.out.println(foo());
    }

    private static void bar(F f) {
        try {
            f.a = 1;
        } finally {
            f.a = 2;
        }
    }

    private static int foo() {
        try {
           return 0;
        } finally {
           return 3;
        }
    }
}

```
Output:
```
2
3
```

#### Explicatie:
* Apelam bar(f).  In try f.a=1, dar in finally f.a=2, pt ca se ajunge in finally indiferent de ce se intampla in try block.
* Afisam f.a=2
* Apelam foo(). In try se incearca return 0, dar finally suprascrie returneaza si va da return 3.
* Afisam 3


### Task 4

Acelasi cu Task 3.