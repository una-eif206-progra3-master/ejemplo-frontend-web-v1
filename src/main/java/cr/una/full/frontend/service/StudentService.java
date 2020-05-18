/*
 *
 * Copyright (C)  2020  mike.education
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Universidad Nacional de Costa Rica, Prof: Maikol Guzman Alan.
 */

package cr.una.full.frontend.service;

import cr.una.full.frontend.Constants;
import cr.una.full.frontend.model.Student;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class StudentService {

    private static final String REST_URI = Constants.WS_ENDPOINT.concat("students");
    private Client client = null;

    /**
     * Empty Constructor
     */
    public StudentService() {
        client = ClientBuilder.newClient();
    }

    /**
     * This method will load all the data from the WS
     *
     * @return the list of Students
     */
    public List<Student> loadAllStudents() {

        // Library Jackson parse JSon
        List<Student> studentList = null;

        studentList = Arrays.asList(client.target(REST_URI).request(MediaType.APPLICATION_JSON)
                .get(Student[].class));

        return studentList;
    }

}
