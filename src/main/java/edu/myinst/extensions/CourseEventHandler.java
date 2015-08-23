package edu.myinst.extensions;

import blackboard.admin.persist.course.CloneConfig;
import blackboard.admin.persist.course.CourseEventListener;
import blackboard.persist.Id;


/**
 * This Class handles course create and course clone events.
 *
 * A course create event occurs when a course is created.
 *
 * A course clone event occurs when course content is copied from one course to another.
 *
 */
public class CourseEventHandler implements CourseEventListener {

    /**
     * executed by the blackboard.cmsadmin.postCloneCourseEventListener extension point.
     *
     * This method is called after a course copy operation.  This might be a full course copy, or
     * it might only be a copy of a subset of course content.
     *
     * @param sourceId The Id of the source course.
     * @param targetId The Id of the target course
     * @param cfg The CloneConfig object which was used when cloning the course.
     * @throws Exception
     */
    public void courseCloned(Id sourceId, Id targetId, CloneConfig cfg) throws Exception {
        // Implement your custom logic here.
        System.out.println("Course Cloned. Source ID: " + sourceId.toExternalString() + " Target ID: " + targetId + " Clong Config: " + cfg.getLoggedOnUser());
    }

    /**
     * executed by the blackboard.cmsadmin.postCreateCourseEventListener extension point.
     *
     * This method is called after a course is created.
     *
     * @param courseId the Id of the newly created course.
     */
    public void courseCreated(Id courseId) {
        // Implement your custom logic here.
        System.out.println("Course Created ID: " + courseId.toExternalString());
    }
}