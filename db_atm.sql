
--删除外码
alter table Account
drop constraint FK_ACCOUNT_RELATIONS_BANKUSER
go

alter table  Account
drop constraint FK_ACCOUNT_RELATIONS_CARDTYPE
go

alter table Operation
drop constraint FK_OPERATIO_RELATIONS_ATM
go

alter table Operation
drop constraint FK_OPERATIO_RELATIONS_SALESMAN
go

alter table Transact
drop constraint FK_TRANSACT_RELATIONS_ACCOUNT
go

alter table Transact
drop constraint FK_TRANSACT_RELATIONS_ATM
go

alter table Transfer
drop constraint FK_TRANSFER_RELATIONS_ACCOUNT
go

alter table Transfer
drop constraint FK_TRANSFER_RELATIONS_ACCOUNT_
go

----------------------------------------------
use db_atm
go

drop table Account
    go

drop table ATM
    go

drop table BankUser
    go

drop table CardType
    go
drop table Operation
    go
drop table Salesman
    go
drop table Transact
    go
drop table Transfer
    go

drop db_atm
go

/*==============================================================*/
/* Database: db_atm                                             */
/*==============================================================*/
create db_atm
go

use db_atm
go

/*==============================================================*/
/* Table: ATM                                                   */
/*==============================================================*/
create table ATM (
                     machine              nvarchar(20)         not null,
                     atmBalance            int                  null,
                     maxBalance            int                  null,
                     constraint PK_ATM primary key (machine)
)
    go

/*==============================================================*/
/* Table: Account                                               */
/*==============================================================*/
create table Account (
                         account              nvarchar(20)         not null,
                         cardId               smallint             not null,
                         userId               nvarchar(20)         not null,
                         password             nvarchar(20)         null,
                         balance              float                null,
                         phoneNumber          nvarchar(11)         null,
                         freeze               bit                  null,
                         constraint PK_ACCOUNT primary key (account)
)
    go

/*==============================================================*/
/* Index: Relationship_1_FK                                     */
/*==============================================================*/




create nonclustered index Relationship_1_FK on Account (cardId ASC)
go

/*==============================================================*/
/* Index: Relationship_2_FK                                     */
/*==============================================================*/




create nonclustered index Relationship_2_FK on Account (userId ASC)
go

/*==============================================================*/
/* Table: BankUser                                              */
/*==============================================================*/
create table BankUser (
                          userId               nvarchar(20)         not null,
                          username             nvarchar(20)         null,
                          sex                  nvarchar(2)          null,
                          idCard               nvarchar(20)         null,
                          openDate             datetime             null,
                          address              nvarchar(50)         null,
                          constraint PK_BANKUSER primary key (userId)
)
    go

/*==============================================================*/
/* Table: CardType                                              */
/*==============================================================*/
create table CardType (
                          cardId               smallint             not null,
                          maxBalance           int                  null,
                          constraint PK_CARDTYPE primary key (cardId)
)
    go

/*==============================================================*/
/* Table: Operation                                             */
/*==============================================================*/
create table Operation (
                           optionId             int                  not null,
                           jobNo                nvarchar(20)         not null,
                           machine              nvarchar(20)         not null,
                           opType               smallint             not null,
                           balance              int                  not null,
                           operatingTime         datetime             not null,
                           constraint PK_OPERATION primary key (optionId)
)
    go

/*==============================================================*/
/* Index: Relationship_7_FK                                     */
/*==============================================================*/




create nonclustered index Relationship_7_FK on Operation (jobNo ASC)
go

/*==============================================================*/
/* Index: Relationship_8_FK                                     */
/*==============================================================*/




create nonclustered index Relationship_8_FK on Operation (machine ASC)
go

/*==============================================================*/
/* Table: Salesman                                              */
/*==============================================================*/
create table Salesman (
                          jobNo                nvarchar(20)         not null,
                          password             nvarchar(20)         null,
                          phone                nvarchar(11)         null,
                          Address              nvarchar(50)         null,
                          name                 nvarchar(20)         not null,
                          constraint PK_SALESMAN primary key (jobNo)
)
    go

/*==============================================================*/
/* Table: Transact                                              */
/*==============================================================*/
create table Transact (
                          tradId               int                  not null,
                          account              nvarchar(20)         not null,
                          machine              nvarchar(20)         not null,
                          tradType             smallint             not null,
                          balance              int                  not null,
                          tradingTime          datetime             not null,
                          constraint PK_TRANSACT primary key (tradId)
)
    go

/*==============================================================*/
/* Index: Relationship_5_FK                                     */
/*==============================================================*/




create nonclustered index Relationship_5_FK on Transact (machine ASC)
go

/*==============================================================*/
/* Index: Relationship_6_FK                                     */
/*==============================================================*/




create nonclustered index Relationship_6_FK on Transact (account ASC)
go

/*==============================================================*/
/* Table: Transfer                                              */
/*==============================================================*/
create table Transfer (
                          transferId           int                  not null,
                          account              nvarchar(20)         not null,
                          targetAccount        nvarchar(20)         not null,
                          balance              float                not null,
                          transferType         smallint             not null,
                          tradingTime          datetime             not null,
                          constraint PK_TRANSFER primary key (transferId)
)
    go

/*==============================================================*/
/* Index: Relationship_3_FK                                     */
/*==============================================================*/




create nonclustered index Relationship_3_FK on Transfer (account ASC)
go

/*==============================================================*/
/* Index: Relationship_4_FK                                     */
/*==============================================================*/




create nonclustered index Relationship_4_FK on Transfer (targetAccount ASC)
go

alter table Account
    add constraint FK_ACCOUNT_RELATIONS_CARDTYPE foreign key (cardId)
        references CardType (cardId)
    go

alter table Account
    add constraint FK_ACCOUNT_RELATIONS_BANKUSER foreign key (userId)
        references BankUser (userId)
    go

alter table Operation
    add constraint FK_OPERATIO_RELATIONS_SALESMAN foreign key (jobNo)
        references Salesman (jobNo)
    go

alter table Operation
    add constraint FK_OPERATIO_RELATIONS_ATM foreign key (machine)
        references ATM (machine)
    go

alter table Transact
    add constraint FK_TRANSACT_RELATIONS_ATM foreign key (machine)
        references ATM (machine)
    go

alter table Transact
    add constraint FK_TRANSACT_RELATIONS_ACCOUNT foreign key (account)
        references Account (account)
    go

alter table Transfer
    add constraint FK_TRANSFER_RELATIONS_ACCOUNT foreign key (account)
        references Account (account)
    go

alter table Transfer
    add constraint FK_TRANSFER_RELATIONS_ACCOUNT_ foreign key (targetAccount)
        references Account (account)
    go
