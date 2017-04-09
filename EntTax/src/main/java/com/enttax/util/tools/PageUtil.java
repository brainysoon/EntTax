package com.enttax.util.tools;

import com.enttax.vo.Page;

/**
 * Created by lcyanxi on 17-4-7.
 */
public class PageUtil {

    /**
     *
     * @param everyPage  每页记录数
     * @param totalCount  总页数
     * @param currentPage 当前页
     * @return
     */
    public static Page createPage(int everyPage,int totalCount,int currentPage){
        Page page=null;
        //设置参数
        everyPage=getEveryPage(everyPage);
        int totalPage=getTotalPage(totalCount ,everyPage);
        currentPage=getCurrentPage(currentPage);
        int beginIndex=getBeginIndex(everyPage,currentPage);
        System.out.println(currentPage);
        boolean hasPrePage=isHasPrePage(currentPage);
        boolean hasNextPage=isHasNextPage(currentPage,totalPage);

        page=new Page(everyPage, totalPage, totalCount, currentPage,
                beginIndex, hasPrePage, hasNextPage) ;
        return page;
    }


    /**
     * 获得每页显示记录数
     * @param everyPage
     * @return
     */
    private static int  getEveryPage(int everyPage){
        return everyPage==0?10:everyPage;
    }


    /**
     * 获得总页数
     * @param totalCount
     * @param everyPage
     * @return
     */
    private static int getTotalPage(int totalCount,int everyPage){
        int totalPage=0;
        if(totalCount/everyPage==0){
            totalPage=totalCount/everyPage;
            if(totalPage>1){
                totalPage=totalCount/everyPage;
            }else{
                totalPage=1;
            }
        }else{
            totalPage=totalCount/everyPage+1;
        }
        return totalPage;
    }

    /**
     * 获得当前页
     * @param currentPage
     * @return
     */
    private static int getCurrentPage(int currentPage){
        return currentPage==0?1:currentPage;
    }


    /**
     * 获得起始点
     * @param everyPage
     * @param currentPage
     * @return
     */
    private static int getBeginIndex(int everyPage ,int currentPage){
        return (currentPage-1)*everyPage;
    }


    /**
     * 是否有上一页
     * @param currentPage
     * @return
     */
    private static boolean isHasPrePage(int currentPage){
        return currentPage==1?false:true;
    }

    /**
     * 是否有下一页
     * @param currentPage
     * @param totalPage
     * @return
     */
    private static boolean isHasNextPage(int currentPage,int totalPage){
        return (currentPage!=totalPage&&totalPage!=0)?true:false;
    }

}
