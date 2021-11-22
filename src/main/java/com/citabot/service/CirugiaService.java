package com.citabot.service;

import com.citabot.interfaceService.ICirugiaService;
import com.citabot.interfaces.ICirugia;
import com.citabot.model.Cirugia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public Boolean delete(int id) {
        boolean b = true;
        try{
            data.deleteById(id);
            return b;
        }catch(Exception error){
            System.out.printf("Error deleting Surgery: ", error.getMessage());
            b= false;

        }
        return b;
    }

    @Override
    public Optional<Cirugia> findById(int id) {
        return data.findById(id);
    }

    @Override
    public Optional<Cirugia> findByDate(Date date) {
        return data.findCirugiasByFechaProcedimiento(date);
    }
}
