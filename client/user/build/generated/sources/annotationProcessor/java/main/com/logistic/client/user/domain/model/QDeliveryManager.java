package com.logistic.client.user.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDeliveryManager is a Querydsl query type for DeliveryManager
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeliveryManager extends EntityPathBase<DeliveryManager> {

    private static final long serialVersionUID = 506259723L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDeliveryManager deliveryManager = new QDeliveryManager("deliveryManager");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> assignmentOrder = createNumber("assignmentOrder", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    //inherited
    public final StringPath deletedBy = _super.deletedBy;

    public final ComparablePath<java.util.UUID> deliveryManagerId = createComparable("deliveryManagerId", java.util.UUID.class);

    public final EnumPath<DeliveryManagerType> deliveryManagerType = createEnum("deliveryManagerType", DeliveryManagerType.class);

    public final ComparablePath<java.util.UUID> hubId = createComparable("hubId", java.util.UUID.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final QUser user;

    public QDeliveryManager(String variable) {
        this(DeliveryManager.class, forVariable(variable), INITS);
    }

    public QDeliveryManager(Path<? extends DeliveryManager> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDeliveryManager(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDeliveryManager(PathMetadata metadata, PathInits inits) {
        this(DeliveryManager.class, metadata, inits);
    }

    public QDeliveryManager(Class<? extends DeliveryManager> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

