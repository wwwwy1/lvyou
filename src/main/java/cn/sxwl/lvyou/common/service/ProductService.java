package cn.sxwl.lvyou.common.service;

import cn.sxwl.lvyou.common.dao.ProductMapper;
import cn.sxwl.lvyou.common.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public List<Product> selectByBid(Integer bid){
        List<Product> list=productMapper.selectByBid(bid);
        return list;
    }
}
