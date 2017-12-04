/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Hasanov (Asus)
 * Created: Oct 16, 2017
 */

drop table if exists users;

create table users (
    id serial primary key,
    fullname text,
    age int
);