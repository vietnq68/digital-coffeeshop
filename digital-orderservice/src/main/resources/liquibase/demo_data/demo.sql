insert into shops (name,owner_id) values ('Hanoi',1);
insert into shops (name,owner_id) values ('Singapore',1);
insert into queues (shop_id,queue_number,size) select id,1,10 from shops where name='Hanoi';
insert into queues (shop_id,queue_number,size) select id,2,10 from shops where name='Hanoi';
insert into queues (shop_id,queue_number,size) select id,3,10 from shops where name='Hanoi';
insert into queues (shop_id,queue_number,size) select id,1,5 from shops where name='Singapore';