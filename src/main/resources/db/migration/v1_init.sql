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
  start_time timestamptz not null,
  end_time timestamptz,
  note text,
  created_at timestamptz not null default now()
);
create index idx_work_sessions_user_time on work_sessions(user_id, start_time desc);

create table login_tokens (
  token varchar(64) primary key,
  user_id uuid not null references users(id) on delete cascade,
  expires_at timestamptz not null,
  used_at timestamptz,
  created_at timestamptz not null default now()
);
create index idx_login_tokens_user on login_tokens(user_id);
