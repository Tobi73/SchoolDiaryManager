package database_lab_work.Controller;

import database_lab_work.App;
import database_lab_work.Interface.EntityControllerInterface;
import database_lab_work.Model.Teacher;
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
@RestController(value= TeacherController.BASE_PATH)
@Api(value = "teacher")
public class TeacherController implements EntityControllerInterface<Teacher> {
    public final static String BASE_PATH = "/" + App.ROOT_PATH + "/" + "Teacher";

    private DatabaseController db = DatabaseController.getInstance();

    @Override
    @RequestMapping(method = RequestMethod.GET, path=BASE_PATH + "/all")
    @ApiOperation(value = "Get data about all teachers", responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Teacher.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<List<Teacher>> getAllData() {
        try{
            return new ResponseEntity(db.<Teacher>getAllData("Teacher"), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    @RequestMapping(method = RequestMethod.GET, path=BASE_PATH+"/getById/{id}")
    @ApiOperation(value = "Get single instance")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Teacher.class),
            @ApiResponse(code = 500, message = "Error"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<Teacher> getById(@PathVariable("id") Long id) {
        try{
            Teacher teacher = db.find(Teacher.class, id);
            if(teacher == null){
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(teacher, HttpStatus.OK);
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
    public ResponseEntity<?> createNewInstance(@RequestBody Teacher entity){
        try{
            db.insert(Teacher.class, entity);
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
    public ResponseEntity<?> updateInstance(@PathVariable("id") Long id, @RequestBody Teacher updatedEntity) {
        try{
            db.update(Teacher.class, id, updatedEntity);
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
            db.remove(Teacher.class, id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}