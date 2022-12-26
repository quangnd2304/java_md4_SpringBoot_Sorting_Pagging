package ra.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.model.entity.Books;
import ra.model.repository.BookRepository;
import ra.model.service.BookService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImp implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Books> sortBookByBookName(String direction) {
        if (direction.equals("asc")) {
            return bookRepository.findAll(Sort.by("bookName").ascending());
        } else {
            return bookRepository.findAll(Sort.by("bookName").descending());
        }
    }

    @Override
    public List<Books> sortByNameAndPrice(String directionName, String directionPrice) {
        if (directionName.equals("asc")) {
            if (directionPrice.equals("asc")) {
                return bookRepository.findAll(Sort.by("bookName").and(Sort.by("price")));
            } else {
                return bookRepository.findAll(Sort.by("bookName").and(Sort.by("price").descending()));
            }
        } else {
            if (directionPrice.equals("asc")) {
                return bookRepository.findAll(Sort.by("bookName").descending().and(Sort.by("price")));
            } else {
                return bookRepository.findAll(Sort.by("bookName").descending().and(Sort.by("price").descending()));
            }
        }

    }

    @Override
    public List<Books> sortByName_Price_Id(String directionName, String directionPrice, String directionId) {
        List<Sort.Order> listOrder = new ArrayList<>();
        if (directionName.equals("asc")){
            listOrder.add(new Sort.Order(Sort.Direction.ASC,"bookName"));
        }else{
            listOrder.add(new Sort.Order(Sort.Direction.DESC,"bookName"));
        }
        if (directionPrice.equals("asc")){
            listOrder.add(new Sort.Order(Sort.Direction.ASC,"price"));
        }else{
            listOrder.add(new Sort.Order(Sort.Direction.DESC,"price"));
        }
        if (directionId.equals("id")){
            listOrder.add(new Sort.Order(Sort.Direction.ASC,"bookId"));
        }else{
            listOrder.add(new Sort.Order(Sort.Direction.DESC,"bookId"));
        }

        return bookRepository.findAll(Sort.by(listOrder));
    }

    @Override
    public Page<Books> getPagging(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
}
