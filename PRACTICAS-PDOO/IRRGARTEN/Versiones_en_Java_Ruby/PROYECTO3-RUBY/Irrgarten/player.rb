# frozen_string_literal: true
#encoding:utf-8

# File: player.rb
# Author: Ángel Rodríguez Faya

require_relative 'shield'
require_relative 'weapon'

module Irrgarten
  class Player
    attr_reader :number, :row, :col
    attr_writer :row, :col

    # Atributos
    @@MAX_WEAPONS = 2
    @@MAX_SHIELDS = 3
    @@INITIAL_HEALTH = 10
    @@HITS2LOSE = 3


    public    # Métodos públicos


      def initialize(number, intelligence, strength)
        @name = "Player #" + @number.to_s
        @number = number.to_s
        @@intelligence = intelligence.to_f
        @@strength = strength.to_f
        @row = 0.to_i
        @col = 0.to_i
        @consecutive_hits = 0.to_i
        @shields = Array.new
        @weapons = Array.new
      end

      def resurrect
        @shields.clear
        @weapons.clear
        @@health = @@INITIAL_HEALTH.to_f
        @consecutive_hits = 0
      end

      # getRow(), getCol(), getNumber() son creados con att.reader
      # setPos(row : int, col : int) es creado con att.writer

      def dead
        return @health == 0.0
      end

      def move(direction, valid_moves) #return Directionsç
        size = valid_moves.size
        contained = valid_moves.include?(direction) #En java es contains, en ruby es .include?

        if size > 0 && !contained
          return valid_moves[0]
        end

        return direction

      end

      def attack # return float
        suma = @strength + private_sum_weapons
        suma.to_f
      end

      def defend(received_attack) #return boolean
        manage_hit(received_attack)
      end

      def receive_reward
        w_reward = Dice.weapons_reward
        s_reward = Dice.shields_reward

        for i in (1..w_reward)
          w_new = new_weapon
          receive_weapon(w_new)
        end

        for i in(1..s_reward)
          s_new = new_shield
          receive_shield(s_new)
        end

        extra_health = Dice.health_reward

        @health += extra_health
      end

      def toString
        "Name: #{@name}, number: #{@number}, intelligence: #{@intelligence}, strength: #{@strength},
          health: #{@health}, row: #{@row}, col: #{@col}, consecutive_hits: #{@consecutive_hits}"
      end


    private   # Métodos privados


    def receive_weapon(w)
      @weapons.each do |wi|
        discard = wi.discard
        if discard
          @weapons.delete(wi)
        end
      end

      size = @weapons.size

      if size < @@MAX_WEAPONS
        @weapons << w
      end

    end

    def receive_shield(s)
      @shields.each do |si|
        discard = si.discard
        if discard
          @shields.delete(si) # En Ruby no existe un remove como tal, sino delete
        end
      end

      size = @shields.size

      if size < @@MAX_SHIELDS
        @shields << s
      end

    end

    def new_weapon #return Weapon
      power = Dice.weapon_power
      uses = Dice.uses_left
      weapon = Weapon.new(power, uses)
      weapon
    end

    def new_shield #return Shield
      protection = Dice.shields_power
      uses = Dice.uses_left
      shield = new Shield(protection, uses)
      shield
    end

    def sum_weapons #return float
      suma = 0.0
      @weapons.each do |weapon|
        suma += weapon.attack
      end
      suma
    end

    def sum_shields #return float
      suma = 0.0
      @shields.each do |shield|
        suma += shield.protect
      end
      suma
    end

    def defensive_energy #return float
      suma = @intelligence + private_sum_shields
      suma.to_f
    end

    def manage_hit(receivedAttack) #return boolean
      defense = defensive_energy
      if defense < receivedAttack
        got_wounded
        inc_consecutive_hits
      else
        reset_hits
      end

      if (@consecutive_hits == @@HITS2LOSE) || (dead)
        reset_hits
        lose = true
      else
        lose = false
      end

      lose #return
    end

    def reset_hits
      @consecutive_hits = 0
    end

    def got_wounded
      @health -= 1
    end

    def inc_consecutive_hits
      @consecutive_hits += 1
    end

  end   # end de la clase Player
end  # end del módulo Irrgarten