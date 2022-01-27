-- 微信用户表
create table tb_wxuser(
      id BIGINT(20) AUTO_INCREMENT,
      user_name VARCHAR(200) not null default '' COMMENT '用户昵称',
      user_head VARCHAR(200) not null default '' COMMENT '用户头像',
      user_openid VARCHAR(200) not null default '' COMMENT 'openid',
      user_unionid VARCHAR(200) default '' COMMENT 'unionid',
      user_lastIp VARCHAR(100) not NULL default '' COMMENT '上次登录IP地址',
      user_last_login_time datetime not null comment '上次登录时间',
      subscribe int not null default 0 comment '是否关注公众号 1：已关注 0：未关注',
      create_time datetime not null COMMENT '创建时间',
      update_time datetime COMMENT '修改时间',
      update_by VARCHAR(100) default '' comment '修改管理员',
      PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 微信回复关键字表
create table tb_wx_key(
      id bigint(20) auto_increment,
      key_word varchar(200) not null default '' comment '关键字',
      msg_type varchar(100) not null default '' comment '回复类型 text/news/image',
      content varchar(200) default '' comment '文本内容',
      mediaID varchar(100) default '' comment '媒体ID',
      primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信回复关键字表';

-- 基础配置数据表
CREATE TABLE `tb_base_data` (
    `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `content` text NOT NULL COMMENT '配置数据内容',
    `sourceType` int(2) NOT NULL DEFAULT '-1' COMMENT '所属类型 0：公众号关注回复',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础配置数据表';

-- 商品表
create table tb_product(
    id bigint(20) not null auto_increment comment '商品id',
    product_name varchar(200) not null default '' comment '商品名称',
    product_cate_id bigint(20) not null default 0 comment '商品类型ID',
    product_sku varchar(20) not null default '' comment '商品SKU',
    product_video varchar(200) default '' comment '商品视频',
    product_pic varchar(200) not null default '' comment '商品主图',
    product_banner varchar(1000) not null default '' comment '商品副图 最多5张',
    product_price decimal(11,2) not null default 0 comment '商品售价',
    product_origin_price decimal(11,2) not null default 0 comment '商品原价',
    product_diy_flag int not null default 0 comment '规格定制标记 0：无定制规格 1：有定制',
    product_desc text not null comment '商品详情',
    product_push_text text not null comment '商品地推文案',
    product_sales int(11) default 0 comment '商品真实销量',
    product_virtual_sales int(11) default 0 comment '商品虚拟销量',
    product_state tinyint(2) not null default 0 comment '商品状态 0-已下架，1-已上架',
    del_flag int(2) not null default 0 comment '删除标记 0：正常，1：删除',
    `create_time`      datetime            DEFAULT NULL COMMENT '添加时间',
    `update_time`      datetime            DEFAULT NULL COMMENT '编辑时间',
    `version`          int(11)             DEFAULT 1 COMMENT '乐观锁',
    update_by varchar(200) default '' comment '操作人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- 商品种类表
create table tb_product_category(
    id bigint(20) AUTO_INCREMENT,
    category_name varchar(100) not null default '' comment '分类名称',
    category_icon varchar(200) default '' comment '分类icon',
    category_flag int not null default 1 comment '分类状态 0：禁用 1：启用',
    parent_id bigint(20) default 0 comment '父级id',
    del_flag int(2) not null default 0 comment '删除标记 0：正常，1：删除',
    `create_time`      datetime            DEFAULT NULL COMMENT '添加时间',
    `update_time`      datetime            DEFAULT NULL COMMENT '编辑时间',
    `version`          int(11)             DEFAULT 1 COMMENT '乐观锁',
    update_by varchar(200) default '' comment '操作人',
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品种类表';

-- 规格属性表 如：性别
create table tb_attributes(
  id bigint(20) AUTO_INCREMENT,
  attribute_name varchar(50) not null default '' comment '属性名',
  del_flag int(2) not null default 0 comment '删除标记 0：正常，1：删除',
  `version`          int(11)             DEFAULT 1 COMMENT '乐观锁',
  update_by varchar(200) default '' comment '操作人',
  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规格属性表';

-- 规格属性值表 如：男、女
create table tb_attributes_value(
    id bigint(20) AUTO_INCREMENT,
    attribute_value varchar(50) not null default '' comment '属性值',
    extra_price decimal(11,2) not null default 0 comment '金额',
    default_flag int(2) not null default 0 comment '默认标记 0：非默认 1：默认',
    attribute_id bigint(11) not null default 0 comment '规格属性ID',
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规格属性值表';

-- 商品规格属性关系表-跟商品相关 如：鞋子 -> 属性：型号
create table tb_product_attributes(
  id bigint(20) AUTO_INCREMENT,
  attribute_id bigint(11) not null default 0 comment '属性ID',
  product_id bigint(11) not null default 0 comment '商品ID',
  product_detail_title varchar(200) not null default '' comment '规格名称',
  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格属性关系表';

-- 商品规格属性关系值表-跟商品相关，相当于商品规格详情，也是商品实体，发生购买商品 ,用户选择完所有属性值后的
-- 如：鞋子 -> 属性：型号的值  男鞋、女鞋
create table tb_product_attributes_value(
    id bigint(20) AUTO_INCREMENT,
    attribute_value_id bigint(11) not null default 0 comment '属性值ID',
    product_id bigint(11) not null default 0 comment '商品ID',
    attribute_id bigint(11) not null default 0 comment '属性ID',
    product_attribute_id bigint(11) not null default 0 comment '商品属性ID',
    extra_price decimal(11,2) not null default 0 comment '附加金额',
    attributes_value_pic varchar(200) not null default '' comment '属性值图片',
    default_flag int(2) not null default 0 comment '默认标记 0：非默认 1：默认',
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格属性关系值表';

-- 订单表
create table tb_shop_order(
  id bigint(11) auto_increment,
  del_flag int(2) not null default 0 comment '删除标记 0：正常，1：删除',
  `version`          int(11)             DEFAULT 1 COMMENT '乐观锁',
  order_no varchar(100) not null default '' comment '订单号',
  user_id bigint(20) not null default 0 comment  '用户ID',
  total_price decimal(11,2) not null default 0 comment '订单总金额',
  order_price decimal(11,2) not null default 0 comment '订单支付金额',
  order_state int not null default -1 comment '订单状态 -1：未支付 0：已取消 1：已支付 2：派送中 3：（已确认）已签收',
  order_des varchar(500) default '' comment '备注',
  addr_pro varchar(100) default '' comment '配送省份',
  addr_city varchar(100) default '' comment '配送城市',
  addr_dis varchar(100) default '' comment '配送区域',
  addr_detail varchar(100) default '' comment '配送详细地址',
  addr_receiver varchar(100) default '' comment '收货人',
  addr_phone varchar(20) default '' comment '联系方式',
  pay_type int default 0 comment '支付方式 0：微信支付',
  create_time datetime not null COMMENT '下单时间',
  pay_time datetime comment '支付时间',
  cancel_time datetime comment '取消时间',
  finish_time datetime comment '完成时间',
  update_time datetime COMMENT '修改时间',
  employee_id bigint(11) not null default 0 comment '员工id',
  total_buy_num int(11) default 0 comment '购买总数量',
  express_no varchar(200) default '' comment '物流单号',
  update_by VARCHAR(100) default '' comment '修改管理员',
  openid varchar(200) not null default '' comment 'openid',
  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- 订单详情表
create table tb_shop_order_item(
   id bigint(11) auto_increment,
   del_flag int(2) not null default 0 comment '删除标记 0：正常，1：删除',
   `version`          int(11)             DEFAULT 1 COMMENT '乐观锁',
   order_no varchar(100) not null default '' comment '订单号',
   product_id bigint(11) not null default 0 comment '商品ID',
   category_id bigint(11) not null default 0 comment '种类ID',
   product_name varchar(200) not null default '' comment '商品名称',
   product_img varchar(200) not null default '' comment '商品规格图片',
   product_price decimal(11,2) default 0 comment '商品金额',
   attributes_value varchar(200) default '' comment '规格属性值名称',
   buy_num int(5) default 0 comment '商品数量',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情表';

-- 系统用户Token
CREATE TABLE sys_user_token (
    user_id BIGINT(20) NOT NULL,
    token varchar(200) NOT NULL,
    expire_time datetime,
    update_time datetime,
    PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token表';
CREATE UNIQUE INDEX index_token on sys_user_token(token);

-- 员工表
create table tb_employee(
    id bigint(20) auto_increment comment '员工id',
    del_flag int(2) not null default 0 comment '删除标记 0：正常，1：删除',
    `version`          int(11)             DEFAULT 1 COMMENT '乐观锁',
    employee_name varchar(100) not null default '' comment '员工姓名',
    account varchar(100) not null default '' comment '登录账号',
    password varchar(200) not null default '' comment '登录密码',
    status tinyint(2) not null default 1 comment '状态 0-已禁用，1-已启用',
    `create_time`      datetime            DEFAULT NULL COMMENT '添加时间',
    `update_time`      datetime            DEFAULT NULL COMMENT '编辑时间',
    update_by varchar(200) default '' comment '操作人',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工表';

-- 限时特惠活动表
create table tb_active_special_price(
    id bigint(20) auto_increment comment '特惠活动id',
    del_flag int(2) not null default 0 comment '删除标记 0：正常，1：删除',
    `version`          int(11)             DEFAULT 1 COMMENT '乐观锁',
    product_id bigint(20) not null default 0 comment '商品id',
    active_price decimal(11,2) not null default 0 comment '活动价格',
    start_time datetime not null comment '活动开始时间',
    end_time datetime not null comment '活动结束时间',
    status tinyint(2) not null default 0 comment '活动状态 0-未开始，1-活动进行中，2-活动已结束',
    `create_time`      datetime            DEFAULT NULL COMMENT '添加时间',
    `update_time`      datetime            DEFAULT NULL COMMENT '编辑时间',
    update_by varchar(200) default '' comment '操作人',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='限时特惠活动表';

-- 出入库表
create table tb_storage_stock(
     id bigint(20) auto_increment comment '出入库id',
     del_flag int(2) not null default 0 comment '删除标记 0：正常，1：删除',
     `version`          int(11)             DEFAULT 1 COMMENT '乐观锁',
     product_id bigint(20) not null default 0 comment '商品id',
     product_attributes_value_id bigint(20) not null default 0 comment '商品某个规格值id，有此值代表仅对商品的某个规格值进行出入库',
     product_number int(11) not null default 0 comment '出入库数量',
     stock_type tinyint(2) not null default 0 comment '出入库类型 0-入库，1-出库',
     stock_content text not null comment '出入库内容',
     `create_time`      datetime            DEFAULT NULL COMMENT '添加时间',
     `update_time`      datetime            DEFAULT NULL COMMENT '编辑时间',
     update_by varchar(200) default '' comment '操作人',
     PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出入库表';

-- 浏览记录表
create table tb_view_log(
  id bigint(20) auto_increment comment '出入库id',
  del_flag int(2) not null default 0 comment '删除标记 0：正常，1：删除',
  `version`          int(11)             DEFAULT 1 COMMENT '乐观锁',
  product_id bigint(20) not null default 0 comment '商品id',
  employee_id bigint(11) not null default 0 comment '员工id',
  user_lastIp VARCHAR(100) not NULL default '' COMMENT 'IP地址',
  `create_time`      datetime            DEFAULT NULL COMMENT '添加时间',
  `update_time`      datetime            DEFAULT NULL COMMENT '编辑时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='浏览记录表';


-- 营业日报表
create table tb_report_business_day(
     id bigint(20) auto_increment comment '出入库id',
     del_flag int(2) not null default 0 comment '删除标记 0：正常，1：删除',
     `version`          int(11)             DEFAULT 1 COMMENT '乐观锁',
     business_day date not null comment '营业日',
     business_start_time datetime not null comment '营业日开始时间',
     business_start_end datetime not null comment '营业日结束时间',
     employee_id bigint(11) not null default 0 comment '员工id',
     total_order int(11) not null default 0 comment '总订单量',
     total_view int(11) not null default 0 comment '总浏览量',
     transfer_rate decimal(5,2) not null default 0 comment '转换率',
     total_amount decimal(11,2) not null default 0 comment '总营业额',
     PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='营业日报表';

-- 域名表
create table tb_realm_name(
  id bigint(20) auto_increment comment '出入库id',
  del_flag int(2) not null default 0 comment '删除标记 0：正常，1：删除',
  `version`          int(11)             DEFAULT 1 COMMENT '乐观锁',
  realm_name varchar(200) not null default '' comment '域名',
  `create_time`      datetime            DEFAULT NULL COMMENT '添加时间',
  `update_time`      datetime            DEFAULT NULL COMMENT '编辑时间',
  update_by varchar(200) default '' comment '操作人',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='域名表';

-- 评论表
create table tb_order_remark(
    id bigint(20) auto_increment comment '评论表id',
    del_flag int(2) not null default 0 comment '删除标记 0：正常，1：删除',
    `version`          int(11)             DEFAULT 1 COMMENT '乐观锁',
    order_no varchar(100) not null default '' comment '订单号',
    user_id bigint(20) not null default 0 comment  '用户ID',
    product_id bigint(20) not null default 0 comment  '商品ID',
    remark_level tinyint(2) not null default 0 comment '星级 0-5',
    user_name varchar(100) not null default '' comment '用户姓名',
    order_remark text not null comment '评论',
    `create_time`      datetime            DEFAULT NULL COMMENT '添加时间',
    `update_time`      datetime            DEFAULT NULL COMMENT '编辑时间',
    update_by varchar(200) default '' comment '操作人',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

-- 订单购买消息表
create table tb_order_msg(
    id bigint(20) auto_increment comment '购买消息id',
    del_flag int(2) not null default 0 comment '删除标记 0：正常，1：删除',
    `version`          int(11)             DEFAULT 1 COMMENT '乐观锁',
    order_no varchar(100) not null default '' comment '订单号',
    user_id bigint(20) not null default 0 comment  '用户ID',
    product_id bigint(20) not null default 0 comment  '商品ID',
    order_msg text not null comment '消息内容',
    `create_time`      datetime            DEFAULT NULL COMMENT '添加时间',
    `update_time`      datetime            DEFAULT NULL COMMENT '编辑时间',
    update_by varchar(200) default '' comment '操作人',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单购买消息表';





