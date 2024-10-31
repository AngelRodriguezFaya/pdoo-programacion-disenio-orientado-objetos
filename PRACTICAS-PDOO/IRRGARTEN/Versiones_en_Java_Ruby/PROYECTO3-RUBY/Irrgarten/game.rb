# frozen_string_literal: true
#encoding:utf-8

# File: game.rb
# Author: Ángel Rodríguez Faya

module Irrgarten
  class Game
    @@MAX_ROUNDS = 10.freeze


    public # Métodos públicos


    def initialize (nplayers)
      #SE DECIDE DE FORMA ALEATORIA.
      # -   Jugador que empieza la partida ---> currentPlayerIndex
      # -   Número de monstruos.
      # -   Posición de la casilla de salida (exitRow, exitCol)

      generator = Random.new

      @log = ""
      @players = Array.new
      @monsters = Array.new

      # Creo y agrego los jugadores al contenedor.
      for i in 0..(nplayers-1)
        number_player = i.chr
        player = Player.new(number_player, Dice.random_intelligence,
                            Dice.random_strength)
        @players.add(player)
      end

      @current_player_index = generator.rand(nplayers.to_i)
      @current_player = @players[@current_player_index]

      # El número de monstruos y la casilla de salida lo haré de forma aleatoria.
      rows = 8.freeze # Número de FILAS de mi laberinto.
      cols = 8.freeze # Número de COLUMNAS de mi laberinto.
      num_monster = generator.rand(10.to_i) + 1
      exit_row    = Dice.random_pos(rows)
      exit_col    = Dice.random_pos(cols)

      # Añado los monstruos
      for i in 0..(num_monster-1)
        name_monster = "Monstruo '" + i.chr + "'"
        monster = Monster.new(name_monster.to_i, Dice.random_intelligence,
                              Dice.random_strength)
        @monsters.add(monster)
      end

      # Instancio el laberinto.
      @labyrinth = Labyrinth.new(rows,cols,exit_row,exit_col)

    end

    def finished #boolean
      @labyrinth.have_a_winner
    end

    def next_step(preferredDirection) #boolean
      @log = ""
      dead = @current_player.dead

      if !dead
        direction = actual_direction(preferredDirection)

        if direction != preferredDirection
          log_player_no_orders
        end

        monster = @labyrinth.put_player(direction, @current_player)

        if monster == nil
          log_no_monster
        else
          winner = combat(monster)
          manage_reward(winner)
        end

      else
        manage_resurrection
      end

      end_game = finished

      if !end_game
        next_player
      end

      end_game
    end

    def get_game_state #GameState
      @game_state = GameState.new("", "", "", @current_player_index, finished, "")
      @game_state
    end


    private # Métodos privados


    def configure_labyrinth
    end

    def next_player
      @current_player_index += 1
      @current_player = @players.get(@current_player_index)
    end

    def actual_direction(preferred_direction) #Directions
      current_row = @current_player.row
      current_col = @current_player.col
      valid_moves = @labyrinth.valid_moves(current_row, current_col)
      output = @current_player.move(preferred_direction, valid_moves)
      output
    end

    def combat(monster) #GameCharacter
      rounds = 0
      winner = GameCharacter.PLAYER
      player_attack = @current_player.attack
      lose = monster.defend(player_attack)
      while !lose && rounds < @@MAX_ROUNDS
        winner = GameCharacter.MONSTER
        rounds += 1
        monster_attack = monster.attack
        lose = @current_player.defend(monster_attack)
        if !lose
          player_attack = @current_player.attack
          winner = GameCharacter.PLAYER
          lose = monster.defend(player_attack)
        end
      end
      log_rounds(rounds, @@MAX_ROUNDS)

      winner
    end

    def manage_reward(winner)
      if winner == GameCharacter.PLAYER
        @current_player.receive_reward
        log_player_won
      else
        log_monster_won
      end
    end

    def manage_resurrection
      resurrect = Dice.resurrect_player
      if resurrect
        @current_player.resurrect
        log_resurrected
      else
        log_player_skip_turn
      end
    end

    def log_player_won
      @log += "El jugador ha ganado el combate. \n"
    end

    def log_monster_won
      @log += "El monstruo ha ganado el combate.\n"
    end

    def log_resurrected
      @log += "El jugador ha resucitado.\n"
    end

    def log_player_skip_turn
      @log += "El jugador ha perdido el turno por estar muerto.\n"
    end

    def log_player_no_orders
      @log += "El jugador no ha podido seguir las instrucciones del jugador humano (no ha sido posible). \n"
    end

    def log_no_monster
      @log += "El jugador se ha movido a una celda vacía o no le ha sido posible moverse.\n"
    end

    def log_rounds(rounds, max)
      @log += "Se han producido #{rounds} de #{max} rondas de combate.\n"
    end

  end   # end de la clase Game
end  # end del módulo Irrgarten
