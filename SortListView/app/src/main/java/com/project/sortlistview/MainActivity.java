package com.project.sortlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.sortlistview.adapter.SortGroupMemberAdapter;
import com.project.sortlistview.entity.GroupMemberBean;
import com.project.sortlistview.util.CharacterParser;
import com.project.sortlistview.util.PinyinComparator;
import com.project.sortlistview.view.ClearEditText;
import com.project.sortlistview.view.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog;
    private ClearEditText mClearEditText;

    private LinearLayout titleLayout;
    private TextView title;
    private TextView tvNofriends;

    private SortGroupMemberAdapter adapter;

    /**
     * 上次第一个可见元素，用于滚动时记录标识
     */
    private int lastFirstVisibleItem = -1;

    private CharacterParser characterParser;
    private List<GroupMemberBean> sourceDataList;

    /**
     * 根据拼音来排列listview里面的数据类
     */
    private PinyinComparator pinyinComparator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        titleLayout = (LinearLayout) findViewById(R.id.title_layout);
        title = (TextView) findViewById(R.id.title_layout_catalog);
        tvNofriends = (TextView) findViewById(R.id.title_layout_no_friends);
        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();

        sideBar = (SideBar) findViewById(R.id.sidrbar);
        dialog = (TextView) findViewById(R.id.dialog);
        sideBar.setTextView(dialog);

        // 设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }
            }
        });

        sortListView = (ListView) findViewById(R.id.country_lvcountry);
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 这里要利用adapter.getItem(position)来获取当前position所对应的的对象
                Toast.makeText(MainActivity.this, ((GroupMemberBean)adapter.getItem(i)).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        String [] data = {"阿妹","阿郎","陈奕迅","周杰伦","曾一鸣","成龙","王力宏","汪峰","王菲","那英","张伟","张学友","李德华","郑源","白水水","白天不亮","陈龙","陈丽丽","哈林","高进","高雷","阮今天","龚琳娜","苏醒","苏永康","陶喆","沙宝亮","宋冬野","宋伟","袁成杰","戚薇","齐大友","齐天大圣","品冠","吴克群","BOBO","Jobs","动力火车","伍佰","#蔡依林","$797835344$","Jack","~夏先生"};
        sourceDataList = filledData(data);

        // 根据a-z进行排序源数据
        Collections.sort(sourceDataList, pinyinComparator);
        adapter = new SortGroupMemberAdapter(this,sourceDataList);
        sortListView.setAdapter(adapter);
        sortListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                int section = getSectionForPosition(firstVisibleItem);
                int nextSection = getSectionForPosition(firstVisibleItem + 1);
                int nextSecPosition = getPositionForSection(+nextSection);
                if (firstVisibleItem != lastFirstVisibleItem) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout.getLayoutParams();
                    params.topMargin = 0;
                    titleLayout.setLayoutParams(params);
                    title.setText(sourceDataList.get(getPositionForSection(section)).getSortLetters());
                }
                if (nextSecPosition == firstVisibleItem + 1) {
                    View childView = view.getChildAt(0);
                    if (childView != null) {
                        int titleHeight = titleLayout.getHeight();
                        int bottom = childView.getBottom();
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout.getLayoutParams();
                        if (bottom < titleHeight) {
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            titleLayout.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                titleLayout.setLayoutParams(params);
                            }
                        }
                    }

                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });
        mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);

        // 根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 这个时候不需要挤压效果，就把他隐藏掉
                titleLayout.setVisibility(View.GONE);
                // 当输入框里面的值为空 更新为原来的列表 否则为过滤数据列表
                filterData(charSequence.toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void filterData(String filterStr) {
        List<GroupMemberBean> filterDataList = new ArrayList<>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDataList = sourceDataList;
            tvNofriends.setVisibility(View.GONE);
        } else {
            filterDataList.clear();
            for (GroupMemberBean sortModer : sourceDataList) {
                String name = sortModer.getName();
                if (name.indexOf(filterStr.toString()) != -1
                        || characterParser.getSelling(name).startsWith(filterStr.toString())) {
                    filterDataList.add(sortModer);
                }

            }
        }

        // 根据 a-z进行排序
        Collections.sort(filterDataList, pinyinComparator);
        adapter.updateListView(filterDataList);
        if (filterDataList.size() == 0) {
            tvNofriends.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 为Listview填充数据
     * @param data
     * @return
     */
    private List<GroupMemberBean> filledData(String[] data) {
        List<GroupMemberBean> mSortList = new ArrayList<>();

        for (int i = 0; i <data.length; i ++) {
            GroupMemberBean sortModel = new GroupMemberBean();
            sortModel.setName(data[i]);
            // 汉字转换拼音
            String pinyin = characterParser.getSelling(data[i]);
            String sortString = pinyin.substring(0,1).toUpperCase();

            // 正则表达式，判断字母是不是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }
            mSortList.add(sortModel);
        }
        return mSortList;
    }

    /**
     * 根据listview的当前位置获取分类的首字母的char ascii值
     * @param position
     * @return
     */
    private int getSectionForPosition(int position) {
        return sourceDataList.get(position).getSortLetters().charAt(0);
    }

    /**
     * 根据分类的首字母的char ascii值获取第一次出现该首字母的位置
     * @param section
     * @return
     */
    private int getPositionForSection(int section) {
        for (int i = 0; i < sourceDataList.size(); i++) {
            String sortStr = sourceDataList.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }
}
