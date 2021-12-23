package com.sky.system.api.remote;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.DictionaryDto;
import com.sky.system.api.vo.DictionaryVO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @date 2020-10-28 028 13:52
 */
public interface DictionaryRemoteService {

    @PostMapping(value = "/dictionary")
    ResponseDto<Boolean> save(@RequestBody DictionaryDto dto);

    @PutMapping(value = "/dictionary")
    ResponseDto<Boolean> update(@RequestBody DictionaryDto dto);

    @PostMapping(value = "/dictionary/list")
    ResponseDto<List<DictionaryVO>> list(@RequestBody DictionaryDto dto);

    @PostMapping(value = "/dictionary/tree")
    ResponseDto<List<DictionaryVO>> tree(@RequestBody DictionaryDto dto);

    @DeleteMapping(value = "/dictionary")
    ResponseDto<Integer> delete(@RequestBody DictionaryDto dto);
}
