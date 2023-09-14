package com.bolbon.utils;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.bolbon.entities.Abilities;
import com.bolbon.entities.Team;
import com.bolbon.entities.Player;

import java.util.Random;

public class CargarDatosEnBaseDeDatos {

    public static void main(String[] args) {
        // Crear una instancia del EntityManagerFactory (asegúrate de que tu unidad de persistencia se llame "myPU")
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

        // Crear un EntityManager
        EntityManager em = emf.createEntityManager();

        // Iniciar una transacción
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // Crear y persistir 20 equipos con 26 jugadores cada uno
            for (int equipoId = 1; equipoId <= 20; equipoId++) {
                Team equipo = new Team("Equipo " + equipoId);
                em.persist(equipo);

                for (int jugadorId = 1; jugadorId <= 26; jugadorId++) {
                    Abilities habilidades = generarHabilidadesAleatorias();
                    Player jugador = new Player(jugadorId, "Jugador " + jugadorId, 25, jugadorId%11, equipo, habilidades);
                    em.persist(jugador);
                }
            }

            // Confirmar la transacción
            tx.commit();
        } catch (Exception e) {
            // Manejar cualquier excepción
            e.printStackTrace();
            tx.rollback();
        } finally {
            // Cerrar el EntityManager y el EntityManagerFactory
            em.close();
            emf.close();
        }
    }

    private static Abilities generarHabilidadesAleatorias() {
        Random rand = new Random();
        int attack = rand.nextInt(100);
        int dribble = rand.nextInt(100);
        int technique = rand.nextInt(100);
        int shot = rand.nextInt(100);
        int shortPass = rand.nextInt(100);
        int longPass = rand.nextInt(100);
        int speed = rand.nextInt(100);
        int agility = rand.nextInt(100);
        int jump = rand.nextInt(100);
        int stamina = rand.nextInt(100);
        int strength = rand.nextInt(100);
        int defence = rand.nextInt(100);

        return new Abilities(attack, dribble, technique, shot, shortPass, longPass, speed, agility, jump, stamina, strength, defence);
    }
}
