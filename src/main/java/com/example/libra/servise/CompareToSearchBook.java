package com.example.libra.servise;

import com.example.libra.domain.Book;

import java.util.Comparator;

class CompareToSearchBook implements Comparator<Book> {
    private final int allTypes;

    public CompareToSearchBook(int allTypes) {
        this.allTypes = allTypes;
    }

    @Override
    public int compare(Book o1, Book o2) {
        if(allTypes==0){
            //популярность
            return o1.getLikes()- o2.getLikes();
        }else if(allTypes==1){
            //дата обновления
            return o1.getDateUpdate().compareTo(o2.getDateUpdate());
        }
        else if(allTypes==2){
            //дата добавления
            return o1.getDatePublish().compareTo(o2.getDatePublish());
        }
        else if(allTypes==3){
            //длинна
            return o1.getLenghtBook()- o2.getLenghtBook();
        }
        else {
            //просмотры
            return o1.getViews()- o2.getViews();
        }
    }
}
