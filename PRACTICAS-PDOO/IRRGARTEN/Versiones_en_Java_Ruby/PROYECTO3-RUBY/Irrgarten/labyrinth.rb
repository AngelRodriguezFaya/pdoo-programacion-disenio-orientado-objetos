# frozen_string_literal: true
#encoding:utf-8

# File: labyrinth.rb
# Author: Ángel Rodríguez Faya

module Irrgarten
  class Labyrinth
    # utilizo .freeze para que se congele y no pueda ser modificada (constante)
    @@BLOCK_CHAR = 'X'.freeze    # ‘X’= obstáculo
    @@EMPTY_CHAR = '-'.freeze    # '-'= casilla vacía
    @@MONSTER_CHAR = 'M'.freeze  # 'M'= monstruo
    @@COMBAT_CHAR = 'C'.freeze   # 'C'= combate entre jugador y monstruo.
    @@EXIT_CHAR = 'E'.freeze     # 'E'= casilla de salida.
    @@ROW = 0.freeze
    @@COL = 1.freeze


    public # Métodos públicos


    # Constructor paramétrico
    def initialize(nr, nc, er, ec)
      # Atributos de instancia
      @n_rows         = nr
      @n_cols         = nc
      @exit_row       = er
      @exit_col       = ec
      # Atributos de relación
      @monster_grid   = Array.new(@n_rows) { Array.new(@n_cols) }
      @character_grid = Array.new(@n_rows) { Array.new(@n_cols) }
      @player_grid    = Array.new(@n_rows) { Array.new(@n_cols) }
    end

    def spread_players(players)   # players es un Player[]
      players.each do |p|
        pos = random_empty_pos
        put_player_2D(-1,-1, pos[@@ROW], pos[@@COL], p)
      end
    end

    # Devuelve si hay un jugador en la casilla de salida
    # @return [Boolean] true si hay ganador, false si no.
    def have_a_winner
      !@player_grid[@exit_row][@exit_col].nil?
    end

    # Método toString
    # @return [String] Devuelve una representación interna del laberinto
    def to_string
      puts "\nLabyrinth{ n_rows= #{@n_rows}, n_cols= #{@n_cols}, " +
             "exit_row= #{@exit_row}, exit_col= #{@exit_row} }.\n"
    end

    # Añade un monstruo al laberinto.
    # Anota su presencia en el tablero de caracteres, guarda la referencia del
    # monstruo en el atributo contendor e indica al mosntruo su posición (setPos).
    #
    # Debe ser una posición que esté dentro del tablero y esté vacía.
    #
    # @param [Integer] row fila
    # @param [Integer] col columna
    # @param [Irrgarten::Monster] monster monstruo
    # @return [void]
    def add_monster(row, col, monster)
      if pos_ok(row, col) && empty_pos(row, col)
        @character_grid[row][col] = @@MONSTER_CHAR
        @monster_grid[row][col]   = monster
        monster.set_pos(row, col)
        puts "\nSe ha añadido un monstruo en la posición (#{row}, #{col}}).\n"
      else
        puts "\nNo se ha podido añadir un monstruo en la posición (#{row}, #{col}}).\n"
      end
    end

    def put_player(direction, player) #Monster
      old_row = player.row
      old_col = player.col
      new_pos = dir_2_pos(old_row, old_col, direction)
      monster = put_player_2D(old_row, old_col, new_pos[@@ROW], new_pos[@@COL], player)
      monster
    end

    def add_block(orientation, start_row, start_col, length)
      if orientation == Orientation.VERTICAL
        inc_row = 1
        inc_col = 0
      else
        inc_row = 0
        inc_col = 1
      end

      row = start_row
      col = start_col

      while pos_ok(row,col) && empty_pos(row,col) && length > 0
        @character_grid[row][col] = @@BLOCK_CHAR
        length -= 1
        row += inc_row
        col += inc_col
      end

    end

    def valid_moves(row, col) #Directions[]
      output = Array.new
      if can_step_on(row+1, col)
        output.add(Directions.DOWN)
      elsif can_step_on(row-1, col)
        output.add(Directions.UP)
      elsif can_step_on(row, col+1)
        output.add(Directions.RIGHT)
      elsif can_step_on(row, col-1)
        output.add(Directions.LEFT)
      end
      output
    end

    def pos_ok(row, col) #boolean
      # Deben ser < que @n_rows y @n_cols, ya que estas son el total de filas.
      # true && true
      !!(row >= 0 && row < @n_rows) && !!(col >= 0 && col < @n_cols)
    end

    def empty_pos(row, col) #boolean
      !!(@character_grid[row][col] == @@EMPTY_CHAR)
    end

    def monster_pos(row, col) #boolean
      char_grid_ok    = !!(@character_grid[row][col] == @@MONSTER_CHAR)
      monster_grid_ok = !!(!@monster_grid[row][col].nil?)
      !!(char_grid_ok && monster_grid_ok)
    end

    def exit_pos(row, col) #boolean
      row_col_ok    = !!((row == @exit_row) && (col == @exit_col))
      char_grid_ok  = !!(@character_grid[row][col] == @@EXIT_CHAR)
      !!(row_col_ok && char_grid_ok)
    end

    def combat_pos(row, col) #boolean
      char_grid_ok  = !!(@character_grid[row][col] == @@COMBAT_CHAR)
      player_ok     = !!(!@player_grid[row][col].nil?)
      monster_ok    = !!(!@monster_grid[row][col].nil?)
      !!(char_grid_ok && player_ok && monster_ok)
    end

    def can_step_on(row, col) #boolean
      if pos_ok(row, col)
        !!(empty_pos(row, col) || monster_pos(row, col) || exit_pos(row, col))
      else
        puts "\nLa posición (#{row}, #{col}) no está dentro del laberinto.\n"
        return false
      end
    end

    def update_old_pos(row, col)
      if pos_ok(row, col)
        if @character_grid[row][col] == @@COMBAT_CHAR
          @character_grid[row][col] = @@MONSTER_CHAR
        else
          @character_grid[row][col] = @@EMPTY_CHAR
        end
      else
        puts "\nLa posición (#{row}, #{col}) no está dentro del laberinto.\n"
      end
    end

    def dir_2_pos(row, col, direction)
      case direction
      when Directions::UP
        row -= 1
      when Directions::DOWN
        row += 1
      when Directions::LEFT
        col -= 1
      when Directions::RIGHT
        col += 1
      end

      [row, col]
    end

    def random_empty_pos
      pos_valida = false
      while !pos_valida
        row_rand = Dice.random_pos(@n_rows)
        col_rand = Dice.random_pos(@n_cols)
        if empty_pos(row_rand, col_rand)
          pos_valida = true
        end
      end

      [row_rand, col_rand]
    end

    def put_player_2D(old_row, old_col, row, col, player) #Monster
      output = nil
      if can_step_on(row, col)
        if pos_ok(row, col)
          p = @player_grid[old_row][old_col]
          if p == player
            update_old_pos(old_row, old_col)
            @player_grid[old_row][old_col] = nil
          end
        end

        monster_pos = monster_pos(row, col)

        if monster_pos
          @character_grid[row][col] = @@COMBAT_CHAR
          output = @monster_grid[row][col]
        else
          number = player.number
          @character_grid[row][col] = number
        end

        @player_grid[row][col] = player
        player.row = row
        player.col = col
      end

      output
    end

  end   # end de la clase Labyrinth
end  # end del módulo Irrgarten