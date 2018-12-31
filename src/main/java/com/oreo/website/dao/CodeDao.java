package com.oreo.website.dao;

import com.oreo.website.until.Code;
import org.springframework.stereotype.Repository;
@Repository
public interface CodeDao {
    Code findCodeByMail(String mail);
    boolean updateCodeByMail(Code code);
    boolean insertCode(Code code);
}
