package sortbox.test;

import sortbox.common.SortedResult;
import sortbox.common.config.SortConfig;
import sortbox.common.pt.PropertyTable;
import sortbox.common.pt.PropertyTableItem;
import sortbox.common.utils.SortItemHelper;
import sortbox.filter.FilterExecutor;
import sortbox.group.Group;
import sortbox.group.GroupExecutor;
import sortbox.pcs.entity.CombineMode;
import sortbox.sort.SortExecutor;
import sortbox.sort.cmp.NameComparator;
import sortbox.sort.cmp.NumberOrderComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test the flow.
 * <p>
 * Created by Clever.Su on 9/20/2017.
 */
public class BizFlowTest {

    public static void main(String... args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(5, "Bin"));
        students.add(new Student(10, "Andrew"));
        students.add(new Student(23, "Den"));
        students.add(new Student(3, "Uzn"));

        List<PropertyTableItem> pList = new PropertyTable<Student>().check(students.get(0)).getPropertyList();
        System.out.println(Arrays.toString(pList.toArray()));

        SortConfig<Student> sortConfig = new SortConfig<Student>().new Builder()
                .set(4, "id", new NumberOrderComparator<>(true))
                .set(2, "tin", new NameComparator<>())
                .combine(CombineMode.SINGLE)
                .useDefaultComparator(false)
                .build();

        SortedResult<Student> result = new SortExecutor<Student>()
                .input(students)
                .preProcess(null)
                .sortConfig(sortConfig)
                .sort();

        System.out.println(result);

//        List<Student> sortedByIdList = SortItemHelper.newInstance().getOriginList(1, result.getMultipleResultSortedMap());
//        System.out.println(Arrays.toString(sortedByIdList.toArray()));
//
//        List<Student> sortedByIdName = SortItemHelper.newInstance().getOriginList("name", result.getMultipleResultSortedMap());
//        System.out.println(Arrays.toString(sortedByIdName.toArray()));
//
//        GroupExecutor gcExecutor = GroupExecutor.newInstance();
//        Group<Student> group = gcExecutor.executeSingle(new PropertyCondition(sortedByIdList, "id_group_1"));
//        System.out.println(group);
//
//        FilterExecutor filterExecutor = FilterExecutor.newInstance();
//        filterExecutor.execute(group.getGroupList(), new RemoveFirstFilter<>());
//
//        System.out.println(group);
    }
}
