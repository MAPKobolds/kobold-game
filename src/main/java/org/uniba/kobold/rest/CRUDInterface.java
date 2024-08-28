package org.uniba.kobold.rest;

/**
 * The interface Crud interface.
 *
 * @param <T> the type parameter
 */
public interface CRUDInterface<T> {
    /**
     * Save t.
     *
     * @param entity the entity
     * @return the t
     */
    T save(T entity);

    /**
     * Delete by id int.
     *
     * @param id the id
     * @return the int
     */
    int deleteById(int id);

    /**
     * Update by id t.
     *
     * @param entity the entity
     * @param id     the id
     * @return the t
     */
    T updateById(T entity, int id);

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    T getById(int id);
}
