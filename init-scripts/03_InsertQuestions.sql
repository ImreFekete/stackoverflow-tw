INSERT INTO questions (question_title, question_text, user_id, created_at)
VALUES
    ('Choosing the Right Programming Language for Web Development',
     'Hey fellow developers! I''m diving into web development and I''m wondering which programming language I should focus on. Any recommendations?',
     1, -- user_id of CodeMaster_42
     NOW()),

    ('Securing Your Digital Life: Best Practices',
     'Cybersecurity is more important than ever. What are some practical steps we can take to protect our digital lives from cyber threats?',
     7, -- user_id of CyberGuardian
     NOW()),

    ('Cloud Computing Options for Startups',
     'As a startup founder, I''m exploring cloud computing to scale my operations. Which cloud platform offers the best services and cost-effectiveness?',
     13, -- user_id of StartupDreamer
     NOW());