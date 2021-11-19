package com.citabot.service;

import com.citabot.interfaceService.ICirugiaService;
import com.citabot.interfaces.ICirugia;
import com.citabot.model.Cirugia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CirugiaService implements ICirugiaService {

    @Autowired
    private ICirugia data;

    @Override
    public List<Cirugia> listar() {
        return (List<Cirugia>) data.findAll();
    }

    @Override
    public Cirugia save(Cirugia cirugia) {
        Cirugia c = new Cirugia();
        try{
            c = data.save(cirugia);
        }catch (Error e){
            System.out.printf("Error insertando: ", e.getCause());
        }


        return c;
    }

    @Override
    public Cirugia update(Cirugia cirugia) {
        Cirugia c = new Cirugia();

        try{
            c = data.save(cirugia);

        }catch (Error e){
            System.out.printf("Error updating: ", e.getMessage());
        }
        return c;
    }

    @Override
    public String delete(int id) {
        Cirugia c = new Cirugia();
        String message = "Sucess";
        try{
            data.deleteById(id);
        }catch (Error e){
            System.out.printf("Error deleting: ", e.getMessage());
            message = "Failed to delete: " + data.findById(id);
        }
        return message;
    }
}
