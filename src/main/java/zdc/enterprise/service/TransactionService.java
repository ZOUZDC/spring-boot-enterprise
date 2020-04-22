package zdc.enterprise.service;

import zdc.enterprise.entity.Student;

public interface TransactionService {


    void t1(Student one, Student two, Student three);
    void t2(Student one, Student two, Student three);
    void t3(Student one, Student two, Student three);
    void t4(Student one, Student two, Student three);
    void t5(Student one, Student two, Student three);
}
