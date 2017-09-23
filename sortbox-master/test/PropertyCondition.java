package sortbox.test;

import sortbox.group.Group;
import sortbox.group.GroupCondition;

import java.util.List;

/**
 * Test GroupCondition with {@link Student}.
 * <p>
 * Created by Clever.Su on 9/20/2017.
 */
public class PropertyCondition extends GroupCondition<Student> {

    private List<Student> studentList;

    /**
     * Default Constructor but can not call it to get new instance.
     * Pass in a list of T as target list to be grouped and initialized the Group object.
     *
     * @param input a list of T to be grouped
     * @param key   string key as group key
     */
    public PropertyCondition(List<Student> input, String key) {
        super(input, key);
        this.studentList = input;
    }

    @Override
    public Group<Student> execute() {
        for (Student student : studentList) {
            if (test(student)) {
                shot(student);
            }
        }
        return getGroup();
    }

    @Override
    public boolean test(Student student) {
        return student.getId() >= 10;
    }
}
