package com.example.onthi1;

public class Student {
    private int id;
    private  String name;
    private int math;
    private int chemistry;
    private int physics;
    public Student(String name, int id, int math, int chemistry, int physics) {
        this.chemistry = chemistry;
        this.id = id;
        this.math = math;
        this.name = name;
        this.physics = physics;
    }

    public int getChemistry() {
        return chemistry;
    }

    public void setChemistry(int chemistry) {
        this.chemistry = chemistry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhysics() {
        return physics;
    }

    public void setPhysics(int physics) {
        this.physics = physics;
    }

    public int Sum(){
        return math + chemistry + physics;
    }
}
