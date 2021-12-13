package cn.com.sheep.workspace.service;

@FunctionalInterface
public interface HelloFunction<T> {

    T operation(int a, int b);

}
