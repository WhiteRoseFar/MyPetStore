package csu.web.MyPetStore.persistence;

import csu.web.MyPetStore.domain.Sequence;

public interface SequenceDao {
    Sequence getSequence(Sequence sequence);
    void updateSequence(Sequence sequence);

}
