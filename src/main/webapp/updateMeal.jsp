<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div class="b-popup">
    <div class="b-popup-content" >
        <form method="POST" action='meals' name="mealFormTwo">
            <div align="right">
                Date/Time : <input height="40px" type="datetime-local" name="dateTime" value="${mealUpdate.dateTime}" />
            </div>
            <div align="right">
                Description : <input type="text" name="description" value="<c:out value="${mealUpdate.description}" />" />
            </div>
            <div align="right">
                Calories : <input type="text" name="calories" value="<c:out value="${mealUpdate.calories}" />" />
            </div>
            <div align="right" style="margin-top: 50px;">
                <input type="submit" value="${mealUpdate.id!=null ? 'Update' : 'Create'} " width="200px"/>
            </div>
            <div align="left">
                <h2 align="left"><a href="meals">Cancel</a></h2>
            </div>
            <input height="40px" type="hidden" readonly="readonly" name="id" value="${mealUpdate.id}" />

        </form>
    </div>
</div>