CREATE OR REPLACE VIEW ELAN_MENUES_DE_VIEW AS
SELECT    c_programm, m.c_menue_parent, m.c_menue_code, m.i_menue_sortierung, m.c_menue_typ, 'D' c_sprache_lut,
      (SELECT t.c_menue_text FROM elan_menues_texte t WHERE t.c_programm = m.c_programm AND t.c_sprache_lut = 'D'
        AND t.c_menue_code = m.c_menue_code) c_menue_text,
            (SELECT t.dec_menue_wert FROM elan_menues_texte t WHERE t.c_programm = m.c_programm AND t.c_sprache_lut = 'D'
        AND t.c_menue_code = m.c_menue_code) dec_menue_wert,
      m.c_objekt, m.c_aktion, m.c_lizenz_id
  FROM    elan_menues m
  START WITH   c_menue_parent = 'ROOT'
  CONNECT BY   m.c_menue_parent = prior m.c_menue_code
  ORDER SIBLINGS BY i_menue_sortierung