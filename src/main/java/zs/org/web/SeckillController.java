package zs.org.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zs.org.dto.Exposer;
import zs.org.dto.SeckillExecution;
import zs.org.dto.SeckillResult;
import zs.org.emums.SeckillStatEnum;
import zs.org.enity.Seckill;
import zs.org.exception.RepeatKillException;
import zs.org.exception.SeckillCloseException;
import zs.org.exception.SeckillException;
import zs.org.service.SeckillService;

import java.util.Date;
import java.util.List;

/**
 * Created by ZhangShan on 2018/10/9.
 */

@Controller
@RequestMapping("/seckill") // url:模块/资源/{}/细分
public class SeckillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        // 页面 + model = modelandview
        List<Seckill> seckillList = seckillService.getSeckillList();
        model.addAttribute("list",seckillList);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detaile",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        // 页面 + model = modelandview

        if(seckillId == null){
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if(seckill == null){
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }


    /* ajax json */
    @RequestMapping(value = "/{seckillId}/exposer",
                    method = RequestMethod.POST,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(Long seckillId){

        SeckillResult<Exposer> seckillResult;

        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            seckillResult = new SeckillResult<Exposer>(true,exposer);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            seckillResult = new SeckillResult<Exposer>(false,e.getMessage());
        }
        return seckillResult;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/exexution",
                    method = RequestMethod.POST,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> exexution(@PathVariable("seckillId") Long seckillID,
                                                     @PathVariable("md5") String md5,
                                                     @CookieValue(value = "killPhone",required = false) Long phone){



        if(phone == null){
            return new SeckillResult<SeckillExecution>(false,"未注册");
        }
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillID, phone, md5);
            return new SeckillResult<SeckillExecution>(true,seckillExecution);
        }catch (RepeatKillException e1){
            return new SeckillResult<SeckillExecution>(false,new SeckillExecution(seckillID, SeckillStatEnum.REPEAT_KILL));
        }catch (SeckillCloseException e2){
            return new SeckillResult<SeckillExecution>(false,new SeckillExecution(seckillID, SeckillStatEnum.END));
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new SeckillResult<SeckillExecution>(false,new SeckillExecution(seckillID, SeckillStatEnum.INNER_ERROR));
        }
    }

    @RequestMapping(value = "/time",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Long> time(){
        Date now = new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }

}
