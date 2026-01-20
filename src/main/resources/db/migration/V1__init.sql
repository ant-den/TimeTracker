create table users (
  id uuid primary key,
  email varchar(320) not null unique,
  is_paid boolean not null default false,
  stripe_customer_id varchar(255),
  paid_at timestamptz,
  created_at timestamptz not null default now()
);

create table work_sessions (
  id uuid primary key,
  user_id uuid not null references users(id) on delete cascade,
  started_at timestamptz not null,
  ended_at timestamptz,
  note text,
  created_at timestamptz not null default now()
);
create index idx_work_sessions_user_time on work_sessions(user_id, started_at desc);

create table login_tokens (
  token varchar(64) primary key,
  user_id uuid not null references users(id) on delete cascade,
  expired_at timestamptz not null,
  used_at timestamptz,
  created_at timestamptz not null default now()
);
create index idx_login_tokens_user on login_tokens(user_id);
