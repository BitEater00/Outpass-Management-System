insert into hostel values('MH1' , 638 , 1);
insert into hostel values('MH2' , 520 , 1);
insert into hostel values('LH1' , 450 , 2);

insert into mentor values('EM7812' , 'Akka' ,'aadesh@vitap.ac.in' ,'LifeGood12');
insert into mentor values('EM7062' , 'Rahul' , 'rahul@vitap.ac.in' , 'home1234');

insert into warden values('EHW2019' , 'Rajesh' , 'rahesh@vitap.ac.in' , '1234' , 'MH2');
insert into warden values('EHW2062' , 'Janya' , 'janya@vitap.ac.in' , '1234' , 'MH1');
insert into warden values('EHW2020' , 'JK' , 'janya@vitap.ac.in' , '1234' , 'LH1');

insert into securityguard values('ES5646','12345');
insert into securityguard values('ES7889','12345');

insert into student values('20BCD7323' , 'Vimal' , 'M' , 'vimal@vitap.ac.in' , '123456' , 1 , 2 , 414 ,  'EM7812' , 'MH1');
insert into student values('21BEC2929' , 'Vimal2' , 'M' , 'vimal@vitap.ac.in' , '123456' , 0 , 0 , 421 , 'EM7812' , 'MH2');
insert into student values('18BFC2020' , 'Vimal3' , 'M' , 'vimal@vitap.ac.in' , '123456' , 1 , 0 , 420 , 'EM7062' , 'MH1');
insert into student values('18SWE6587' , 'Vimal4' , 'M' , 'vimal@vitap.ac.in' , '123456' , 1 , 1 , 414 , 'EM7062' , 'LH1');
insert into student values('20BES7656' , 'Vimal5' , 'M' , 'vimal@vitap.ac.in' , '123456' , 0 , 0 , 456 , 'EM7812' , 'LH1');


insert into request(requestid,studentid,destination,fromdate,todate,intime,outtime,internalcomment,status,requesttype) values ('20BCD732301012020010101','20BCD7323','Kanpur',TO_DATE('23-01-2020' , 'dd-mm-yyyy'),TO_DATE('07-02-2020' , 'dd-mm-yyyy'),NULL,NULL,'NULL',2,1);
insert into request(requestid,studentid,destination,fromdate,todate,intime,outtime,internalcomment,status,requesttype) values ('21BEC292901012020010102','21BEC2929','Srinagar',TO_DATE('02-06-2019' , 'dd-mm-yyyy'),TO_DATE('17-06-2019' , 'dd-mm-yyyy'),NULL,NULL,'NULL',3,1);
insert into request(requestid,studentid,destination,fromdate,todate,intime,outtime,internalcomment,status,requesttype) values ('18BFC202001012020010103','18BFC2020','Mangaore',TO_DATE('30-11-2019' , 'dd-mm-yyyy'),TO_DATE('11-12-2019' , 'dd-mm-yyyy'),NULL,NULL,'NULL',2,2);
insert into request(requestid,studentid,destination,fromdate,todate,intime,outtime,internalcomment,status,requesttype) values ('18SWE658401012020010104','18SWE6587','Patna',TO_DATE('14-12-2020' , 'dd-mm-yyyy'),TO_DATE('21-12-2020' , 'dd-mm-yyyy'),NULL,NULL,'NULL',4,2);
insert into request(requestid,studentid,destination,fromdate,todate,intime,outtime,internalcomment,status,requesttype) values ('21BEC292901012020010105','21BEC2929','Bengaluru',TO_DATE('02-06-2020' , 'dd-mm-yyyy'),TO_DATE('16-06-2020' , 'dd-mm-yyyy'),NULL,NULL,'NULL',1,3);
insert into request(requestid,studentid,destination,fromdate,todate,intime,outtime,internalcomment,status,requesttype) values ('20BES765601012020010106','20BES7656','Coimbatore',TO_DATE('11-01-2020' , 'dd-mm-yyyy'),TO_DATE('21-01-2020' , 'dd-mm-yyyy'),NULL,NULL,'NULL',5,3);
insert into request(requestid,studentid,destination,fromdate,todate,intime,outtime,internalcomment,status,requesttype) values ('18BFC202001012020010107','18BFC2020','Hyderabad',TO_DATE('30-12-2019' , 'dd-mm-yyyy'),TO_DATE('31-12-2019' , 'dd-mm-yyyy'),NULL,NULL,'NULL',2,2);
insert into request(requestid,studentid,destination,fromdate,todate,intime,outtime,internalcomment,status,requesttype) values ('18SWE658701012020010108','18SWE6587','Delhi',TO_DATE('15-09-2020' , 'dd-mm-yyyy'),TO_DATE('21-09-2020' , 'dd-mm-yyyy'),NULL,NULL,'Parent approval not recieved',4,2);
insert into request(requestid,studentid,destination,fromdate,todate,intime,outtime,internalcomment,status,requesttype) values ('21BEC292901012020010109','21BEC2929','Jaipur',TO_DATE('02-06-2020' , 'dd-mm-yyyy'),TO_DATE('10-06-2020' , 'dd-mm-yyyy'),NULL,NULL,'NULL',1,3);
insert into request(requestid,studentid,destination,fromdate,todate,intime,outtime,internalcomment,status,requesttype) values ('20BES765601012020010110','20BES7656','Kolkata',TO_DATE('05-01-2020' , 'dd-mm-yyyy'),TO_DATE('20-01-2020' , 'dd-mm-yyyy'),NULL,NULL,'NULL',5,3);
insert into request(requestid,studentid,destination,fromdate,todate,intime,outtime,internalcomment,status,requesttype) values ('18SWE658701012020010111','18SWE6587','Agra',TO_DATE('14-12-2020' , 'dd-mm-yyyy'),TO_DATE('21-12-2020' , 'dd-mm-yyyy'),NULL,NULL,'Incorrect information added',4,2);
insert into request(requestid,studentid,destination,fromdate,todate,intime,outtime,internalcomment,status,requesttype) values ('21BEC292901012020010111','21BEC2929','Bengaluru',TO_DATE('02-07-2020' , 'dd-mm-yyyy'),TO_DATE('26-07-2020' , 'dd-mm-yyyy'),NULL,NULL,'NULL',1,3);
insert into request(requestid,studentid,destination,fromdate,todate,intime,outtime,internalcomment,status,requesttype) values ('21BEC292901012020010112','21BEC2929','Hyderabad',TO_DATE('01-03-2020' , 'dd-mm-yyyy'),TO_DATE('06-03-2020' , 'dd-mm-yyyy'),NULL,NULL,'NULL',1,2);
insert into request(requestid,studentid,destination,fromdate,todate,intime,outtime,internalcomment,status,requesttype) values ('18BFC202001012020010101','18BFC2020','Gazhiabad',TO_DATE('02-02-2021' , 'dd-mm-yyyy'),TO_DATE('09-02-2021' , 'dd-mm-yyyy'),NULL,NULL,'NULL',2,2);
insert into request(requestid,studentid,destination,fromdate,todate,intime,outtime,internalcomment,status,requesttype) values ('20BCD732301012020010101','20BCD7323','Gazhiabad',TO_DATE('04-04-2020' , 'dd-mm-yyyy'),TO_DATE('08-04-2020' , 'dd-mm-yyyy'),NULL,NULL,'FAT Happening',4,2);
insert into request(requestid,studentid,destination,fromdate,todate,intime,outtime,internalcomment,status,requesttype) values ('20BES765601012025010101','20BES7656','Shimla',TO_DATE('12-07-2019' , 'dd-mm-yyyy'),TO_DATE('18-07-2019' , 'dd-mm-yyyy'),NULL,NULL,'NULL',3,3);
insert into request(requestid,studentid,destination,fromdate,todate,intime,outtime,internalcomment,status,requesttype) values ('21BEC292978012020010102','21BEC2929','Pune',TO_DATE('01-05-2019' , 'dd-mm-yyyy'),TO_DATE('11-05-2019' , 'dd-mm-yyyy'),NULL,NULL,'Might loose his attendance',4,1);
insert into request(requestid,studentid,destination,fromdate,todate,intime,outtime,internalcomment,status,requesttype) values ('21BEC292901014020010102','21BEC2929','Ingulu',TO_DATE('11-07-2020' , 'dd-mm-yyyy'),TO_DATE('14-07-2020' , 'dd-mm-yyyy'),NULL,NULL,'NULL',1,1);
insert into request(requestid,studentid,destination,fromdate,todate,intime,outtime,internalcomment,status,requesttype) values ('20BES765601012060010102','20BES7656','Amritsar',TO_DATE('11-07-2020' , 'dd-mm-yyyy'),TO_DATE('14-07-2020' , 'dd-mm-yyyy'),NULL,NULL,'NULL',2,2);

delete from request;
delete from student;
delete from warden;
delete from mentor;
delete from hostel;

commit;