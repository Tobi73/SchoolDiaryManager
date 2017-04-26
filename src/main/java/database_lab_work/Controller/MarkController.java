package database_lab_work.Controller;

import database_lab_work.App;
import database_lab_work.Interface.EntityControllerInterface;
import database_lab_work.Model.Mark;
import database_lab_work.Repository.DatabaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gman0_000 on 27.04.2017.
 */
@RestController(value= MarkController.BASE_PATH)
@Api(value = "mark")
public class MarkController implements EntityControllerInterface<Mark> {
    public final static String BASE_PATH = "/" + App.ROOT_PATH + "/" + "mark";

    private DatabaseController db = DatabaseController.getInstance();

    @Override
    @RequestMapping(method = RequestMethod.GET, path=BASE_PATH + "/all")
    @ApiOperation(value = "Get data about all marks", responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Mark.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<List<Mark>> getAllData() {
        try{
            return new ResponseEntity(db.<Mark>getAllData("Mark"), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    @RequestMapping(method = RequestMethod.GET, path=BASE_PATH+"/getById/{id}")
    @ApiOperation(value = "Get single instance")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Mark.class),
            @ApiResponse(code = 500, message = "Error"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<Mark> getById(@PathVariable("id") Long id) {
        try{
            Mark mark = db.find(Mark.class, id);
            if(mark == null){
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(mark, HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, path=BASE_PATH + "/create")
    @ApiOperation(value = "Create new instance")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 503, message = "Error"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<?> createNewInstance(@RequestBody Mark entity){
        try{
            db.insert(Mark.class, entity);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, path=BASE_PATH + "/update/{id}")
    @ApiOperation(value = "Update instance")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 503, message = "Error"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<?> updateInstance(@PathVariable("id") Long id, @RequestBody Mark updatedEntity) {
        try{
            db.update(Mark.class, id, updatedEntity);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, path=BASE_PATH + "/remove/{id}")
    @ApiOperation(value = "Remove instance from database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<?> removeInstance(@PathVariable("id") Long id) {
        try{
            db.remove(Mark.class, id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
