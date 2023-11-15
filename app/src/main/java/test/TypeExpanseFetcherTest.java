package test;

import async.TypeExpanseFetcher;
import listener.OnTypeExpanseFetchListener;
import persistence.models.TypeExpanseModel;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TypeExpanseFetcherTest {

    @Test
    public void testFetchTypeExpanses() {
        TypeExpanseFetcher fetcher = new TypeExpanseFetcher(null, new OnTypeExpanseFetchListener() {
            @Override
            public void onTypeExpanseFetchSuccess(List<TypeExpanseModel> typeExpanses) {
                assertNotNull(typeExpanses); // Verifica se a lista não é nula
                assertTrue(typeExpanses.size() > 0); // Verifica se a lista contém dados
                // Aqui você pode adicionar mais verificações conforme necessário
            }

            @Override
            public void onTypeExpanseFetchError() {
                fail("Erro ao buscar os tipos de despesa"); // Se ocorrer um erro, falha no teste
            }
        });

        fetcher.fetchTypeExpanses();
    }
}
