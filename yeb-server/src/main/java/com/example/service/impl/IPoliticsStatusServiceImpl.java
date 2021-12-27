package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.PoliticsStatusMapper;
import com.example.pojo.PoliticsStatus;
import com.example.service.IPoliticsStatusService;
import org.springframework.stereotype.Service;

@Service
public class IPoliticsStatusServiceImpl extends ServiceImpl<PoliticsStatusMapper, PoliticsStatus> implements IPoliticsStatusService {
}
