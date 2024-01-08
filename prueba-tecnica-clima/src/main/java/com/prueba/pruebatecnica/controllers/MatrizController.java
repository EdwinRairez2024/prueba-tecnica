package com.prueba.pruebatecnica.controllers;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.pruebatecnica.models.entity.MatrizRequest;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class MatrizController {
	
	@PostMapping("/multiplicar-matrices")
    public ResponseEntity<?> multiplicarMatrices(@RequestBody MatrizRequest request) {
        // Validaciones y lógica de multiplicación de matrices
        return multiplicarConcurrentemente(request.getMatriz1(), request.getMatriz2());
    }
	
	private ResponseEntity<?> multiplicarConcurrentemente(int[][] matriz1, int[][] matriz2) {
        int filas1 = matriz1.length;
        int columnas1 = matriz1[0].length;
        int filas2 = matriz2.length;
        int columnas2 = matriz2[0].length;
        Map<String,Object> response = new HashMap<>();
        int[][] resultado = null;
        if (columnas1 == filas2) {
            resultado = new int[filas1][columnas2];

            Thread[][] threads = new Thread[filas1][columnas2];

            for (int i = 0; i < filas1; i++) {
                for (int j = 0; j < columnas2; j++) {
                    threads[i][j] = new Thread(new MultiplicacionThread(i, j, matriz1, matriz2, resultado));
                    threads[i][j].start();
                }
            }

            try {
                // Esperar a que todos los hilos terminen
                for (int i = 0; i < filas1; i++) {
                    for (int j = 0; j < columnas2; j++) {
                        threads[i][j].join();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            response.put("Resultado", resultado);
            return new ResponseEntity<Map<String , Object>>(response, HttpStatus.OK);
        }
        response.put("Error","Las matrices no pueden ser multiplicadas, debido que el numero de columnas de la primera matriz no es igual al numero de filas de la segunda matriz");    
        return new ResponseEntity<Map<String , Object>>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    private static class MultiplicacionThread implements Runnable {
        private final int fila;
        private final int columna;
        private final int[][] matriz1;
        private final int[][] matriz2;
        private final int[][] resultado;

        public MultiplicacionThread(int fila, int columna, int[][] matriz1, int[][] matriz2, int[][] resultado) {
            this.fila = fila;
            this.columna = columna;
            this.matriz1 = matriz1;
            this.matriz2 = matriz2;
            this.resultado = resultado;
        }

        @Override
        public void run() {
            int suma = 0;
            for (int k = 0; k < matriz1[0].length; k++) {
                suma += matriz1[fila][k] * matriz2[k][columna];
            }
            resultado[fila][columna] = suma;
        }
    }
}


