package com.sky.system.api.remote;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.DictionaryDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @date 2020-10-28 028 13:52
 */
public interface DictionaryRemoteService {

    @PostMapping(value = "/dictionary/save")
    ResponseDto<Boolean> save(@RequestBody DictionaryDto dto);

    @PostMapping(value = "/dictionary/update")
    ResponseDto<Boolean> update(@RequestBody DictionaryDto dto);

    @PostMapping(value = "/dictionary/list")
    ResponseDto<List<DictionaryDto>> list();

    @PostMapping(value = "/dictionary/delete")
    ResponseDto<Integer> delete(@RequestBody DictionaryDto dto);
}
