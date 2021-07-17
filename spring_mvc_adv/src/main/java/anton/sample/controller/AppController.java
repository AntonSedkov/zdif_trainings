package anton.sample.controller;

import anton.sample.dao.AdvDao;
import anton.sample.entity.Advertisement;
import anton.sample.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: Sedkov Anton
 * Date: 17.07.2021
 */
@Controller
@RequestMapping("/adv")
public class AppController {
    @Autowired
    private AdvDao advDao;

    @RequestMapping("/")
    public ModelAndView listAllAdvs() {
        return new ModelAndView("index", "advs", advDao.listAll());
    }

    @PostMapping("/add_adv")
    public String addAdvertisement() {
        return "add_adv";
    }

    @PostMapping("/search")
    public ModelAndView search(@RequestParam(value = "pattern") String pattern) {
        return new ModelAndView("index", "advs", advDao.list(pattern));
    }

    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam(value = "id") long id) {
        advDao.delete(id);
        return new ModelAndView("index", "advs", advDao.listAll());
    }

    @RequestMapping("/image/{file_id}")
    public void getFile(HttpServletRequest request, HttpServletResponse response,
                        @PathVariable("file_id") long fileId) {
        try {
            byte[] content = advDao.getPhoto(fileId);
            response.setContentType("image/png");
            response.getOutputStream().write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/add")
    public ModelAndView addAdv(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "shortDesc") String shortDesc,
            @RequestParam(value = "longDesc", required = false) String longDesc,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "price") double price,
            @RequestParam(value = "photo") MultipartFile photo,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            Advertisement adv = new Advertisement(name, shortDesc, longDesc, phone, price,
                    photo.isEmpty() ? null : new Photo(photo.getOriginalFilename(), photo.getBytes()));
            advDao.add(adv);
            return new ModelAndView("index", "advs", advDao.listAll());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
    }
}
